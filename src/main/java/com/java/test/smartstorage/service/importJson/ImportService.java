package com.java.test.smartstorage.service.importJson;

import com.java.test.smartstorage.config.importSettings.ImportSettings;
import com.java.test.smartstorage.model.identifiable.Identifiable;
import com.java.test.smartstorage.service.queryable.importable.ImportableService;
import org.springframework.web.multipart.MultipartFile;

import java.io.OutputStream;

public interface ImportService {
    <T extends Identifiable, R> void initializeImportProcess(MultipartFile file, ImportableService importableService, ImportSettings<T, R> importSettings, OutputStream outputStream);
}
