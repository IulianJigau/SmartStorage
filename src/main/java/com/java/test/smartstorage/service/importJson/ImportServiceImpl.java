package com.java.test.smartstorage.service.importJson;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SequenceWriter;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.java.test.smartstorage.config.importSettings.ImportSettings;
import com.java.test.smartstorage.exception.ControlledException;
import com.java.test.smartstorage.exception.controlledException.ResourceValidationException;
import com.java.test.smartstorage.model.Process;
import com.java.test.smartstorage.model.identifiable.Identifiable;
import com.java.test.smartstorage.model.intermediary.OpenedFile;
import com.java.test.smartstorage.service.queryable.importable.ImportableService;
import com.java.test.smartstorage.util.Utility;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.postgresql.PGConnection;
import org.postgresql.copy.CopyManager;
import org.springframework.jdbc.datasource.DataSourceUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.method.annotation.StreamingResponseBody;

import javax.sql.DataSource;
import java.io.*;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.function.Consumer;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

@Service
@RequiredArgsConstructor
public class ImportServiceImpl implements ImportService {
    private static final String JSON_EXTENSION = ".json";
    private final ObjectMapper objectMapper;
    private final DataSource dataSource;
    private CsvMapper csvMapper;

    @PostConstruct
    private void init() {
        csvMapper = CsvMapper.builder()
                .addModule(new JavaTimeModule())
                .disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS)
                .build();
    }

    private void copyFromPipe(CopyManager copyManager, PipedReader pipedReader, String sqlCopy) {
        try {
            copyManager.copyIn(sqlCopy, pipedReader);
        } catch (IOException e) {
            throw new RuntimeException("Failed to initialize the copy sequence", e);
        } catch (SQLException e) {
            Utility.checkSQLExceptionCode(e);
        }
    }

    private <R> void writeSequence(SequenceWriter sequenceWriter, List<R> data) {
        if (data == null) return;
        try {
            for (R dataRow : data) {
                sequenceWriter.write(dataRow);
            }
        } catch (IOException e) {
            throw new ResourceValidationException("Mismatched data structure");
        }
    }

    private <T> T mapJsonObject(JsonParser parser, Class<T> inputClass) {
        try {
            return objectMapper.readValue(parser, inputClass);
        } catch (IOException e) {
            throw new ResourceValidationException("Json structure is not valid");
        }
    }

    private void validateJsonArrayStart(JsonParser parser) throws IOException {
        if (parser.nextToken() != JsonToken.START_ARRAY) {
            throw new ResourceValidationException("Json should be an array");
        }
    }

    private void iterateJsonArray(JsonParser parser, Consumer<JsonParser> processObject) {
        try {
            validateJsonArrayStart(parser);
            while (parser.nextToken() == JsonToken.START_OBJECT) {
                processObject.accept(parser);
            }
        } catch (IOException e) {
            throw new ResourceValidationException("Json object is corrupted");
        }
    }

    private void executeUsingJsonParser(InputStream inputStream, Consumer<JsonParser> parserConsumer) {
        try (JsonParser parser = objectMapper.getFactory().createParser(inputStream)) {
            parserConsumer.accept(parser);
        } catch (IOException e) {
            throw new ResourceValidationException("Json structure is not valid");
        }
    }

    private void validateFileName(String name, String extension) {
        if (!name.toLowerCase().endsWith(extension)) {
            throw new ResourceValidationException("Only .json files are allowed");
        }
    }

    private void executeOnExtraction(MultipartFile multipartFile, Consumer<OpenedFile> processFile) {
        try (InputStream is = multipartFile.getInputStream();
             BufferedInputStream bis = new BufferedInputStream(is);
             ZipInputStream zis = new ZipInputStream(bis)) {

            ZipEntry entry;
            while ((entry = zis.getNextEntry()) != null) {
                if (entry.isDirectory()) continue;

                ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
                zis.transferTo(outputStream);
                InputStream inputStream = new ByteArrayInputStream(outputStream.toByteArray());

                OpenedFile openedFile = new OpenedFile(entry.getName(), inputStream);
                processFile.accept(openedFile);

                zis.closeEntry();
            }
        } catch (IOException e) {
            throw new ResourceValidationException("Failed to read the zip file");
        }
    }

    private <R> void executeUsingSequenceWriter(PipedWriter pipedWriter, Class<R> outputClass, Consumer<SequenceWriter> writerConsumer) {
        CsvSchema schema = csvMapper.schemaFor(outputClass).withHeader();
        try (SequenceWriter sequenceWriter = csvMapper.writer(schema).writeValues(pipedWriter)) {
            writerConsumer.accept(sequenceWriter);
        } catch (IOException e) {
            throw new RuntimeException("Failed to crate the SequenceWriter", e);
        }
    }

    private void initializeReadWrite(Consumer<PipedWriter> writer, Consumer<PipedReader> reader) {
        try (PipedWriter pipedWriter = new PipedWriter();
             PipedReader pipedReader = new PipedReader(pipedWriter);
             ExecutorService executor = Executors.newSingleThreadExecutor()) {

            Future<?> future = executor.submit(() -> writer.accept(pipedWriter));
            reader.accept(pipedReader);
            future.get();
        } catch (ExecutionException | InterruptedException e) {
            try {
                throw e.getCause();
            } catch (Throwable ex) {
                throw new RuntimeException("Executor error report failure", ex);
            }
        } catch (IOException e) {
            throw new RuntimeException("Failed to initialize the PipedWriter/Reader", e);
        }
    }

    private void executeWithCopyManager(Consumer<CopyManager> copyManagerConsumer) {
        try (Connection conn = DataSourceUtils.getConnection(dataSource)) {
            conn.setAutoCommit(false);

            PGConnection pgConn = conn.unwrap(PGConnection.class);
            CopyManager copyManager = pgConn.getCopyAPI();

            try {
                copyManagerConsumer.accept(copyManager);
            } catch (Exception e) {
                conn.rollback();
                throw e;
            }

            conn.commit();
        } catch (SQLException e) {
            throw new RuntimeException("Failed to get the database copy manager", e);
        }
    }

    public <T extends Identifiable, R> void importEntity(MultipartFile file, OutputStream outputStream, Process process, ImportSettings<T, R> importSettings) {
        executeWithCopyManager(
                copyManager -> initializeReadWrite(
                        pipedWriter -> executeUsingSequenceWriter(
                                pipedWriter,
                                importSettings.getOutputClass(),
                                sequenceWriter -> executeOnExtraction(
                                        file,
                                        openedFile -> {
                                            validateFileName(openedFile.getName(), JSON_EXTENSION);
                                            executeUsingJsonParser(
                                                    openedFile.getInputStream(),
                                                    parser_array -> iterateJsonArray(
                                                            parser_array,
                                                            parser_object ->
                                                            {
                                                                T object = mapJsonObject(parser_object, importSettings.getInputClass());
                                                                object.setProcessId(process.getId());
                                                                List<R> transformedObject = importSettings.transform(object);
                                                                writeSequence(sequenceWriter, transformedObject);
                                                            }
                                                    )
                                            );
                                            process.incrementProcessedFiles();
                                            Utility.writeOutput("Files processed: " + process.getFilesProcessed(), outputStream);
                                        }
                                )
                        ),
                        pipedReader -> copyFromPipe(
                                copyManager,
                                pipedReader,
                                importSettings.getSqlCopyQuery()

                        )
                )
        );
    }

    @Override
    public <T extends Identifiable, R> StreamingResponseBody initializeImportProcess(MultipartFile file, ImportableService importableService, ImportSettings<T, R> importSettings) {
        Process process = new Process().initialize();

        return outputStream -> {
            try {
                Utility.writeOutput("Dropping the index", outputStream);
                importableService.dropIndex();

                Utility.writeOutput("Processing files", outputStream);
                importEntity(file, outputStream, process, importSettings);

                if (importableService.checkIndexingProcessExistence()) {
                    process.setCompleted("Indexing and deduplication skipped due to them being already in progress");
                } else {
                    Utility.writeOutput("Creating the index", outputStream);
                    importableService.createIndex();

                    process.setDeduplicating();
                    Utility.writeOutput("Removing Duplicates", outputStream);
                    importableService.removeDuplicates();

                    process.setCompleted("Import completed successfully");
                }
            } catch (ControlledException e) {
                process.setFailed(e.getMessage());
            } catch (Exception e) {
                process.setFailed("Critical error occurred");
                throw e;
            }
        };
    }
}
