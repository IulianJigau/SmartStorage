package com.java.test.smartstorage.service.queryableService.importableService;

import com.java.test.smartstorage.config.importSettings.ImportSettings;
import com.java.test.smartstorage.mapper.importableMapper.ImportableMapper;
import com.java.test.smartstorage.model.identifiable.Identifiable;
import com.java.test.smartstorage.service.ImportService;
import com.java.test.smartstorage.service.ImportServiceImpl;
import com.java.test.smartstorage.service.queryableService.QueryableServiceAbs;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.method.annotation.StreamingResponseBody;

public abstract class ImportableServiceAbs<T extends Identifiable, R> extends QueryableServiceAbs implements ImportableService {
    private final ImportableMapper importableMapper;
    private final ImportService importService;
    private final ImportSettings<T, R> importSettings;

    public ImportableServiceAbs(ImportableMapper importableMapper, ImportServiceImpl importService, ImportSettings<T, R> importSettings) {
        super(importableMapper);
        this.importableMapper = importableMapper;
        this.importService = importService;
        this.importSettings = importSettings;
    }

    @Override
    public boolean checkIndexingProcessExistence() {
        return importableMapper.checkIndexingProcessExistence();
    }

    @Override
    public void dropIndex() {
        importableMapper.dropIndex();
    }

    @Override
    public void createIndex() {
        importableMapper.createIndex();
    }

    @Override
    public StreamingResponseBody importFromArchive(MultipartFile file) {
        return importService.initializeImportProcess(file, this, importSettings);
    }

    @Override
    public void removeDuplicates() {
        importableMapper.removeDuplicates();
    }
}
