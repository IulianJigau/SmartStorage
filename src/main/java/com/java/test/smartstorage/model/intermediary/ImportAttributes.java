package com.java.test.smartstorage.model.intermediary;

import com.java.test.smartstorage.config.importSettings.ImportSettings;
import com.java.test.smartstorage.model.identifiable.Identifiable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.web.multipart.MultipartFile;

import java.io.OutputStream;

@Getter
@AllArgsConstructor
public class ImportAttributes <T extends Identifiable, R>{
    private MultipartFile file;
    private ImportSettings<T, R> importSettings;
    private OutputStream outputStream;
}
