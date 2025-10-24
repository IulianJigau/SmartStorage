package com.java.test.smartstorage.service.importJson;

import com.java.test.smartstorage.config.importSettings.ImportSettings;
import com.java.test.smartstorage.model.identifiable.Identifiable;
import com.java.test.smartstorage.service.queryable.importable.ImportableService;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.method.annotation.StreamingResponseBody;

public interface ImportService {
    <T extends Identifiable, R> StreamingResponseBody initializeImportProcess(MultipartFile file, ImportableService importableService, ImportSettings<T, R> importSettings);
}
