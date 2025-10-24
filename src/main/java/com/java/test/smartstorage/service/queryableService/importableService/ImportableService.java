package com.java.test.smartstorage.service.queryableService.importableService;

import com.java.test.smartstorage.service.queryableService.QueryableService;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.method.annotation.StreamingResponseBody;

public interface ImportableService extends QueryableService {
    boolean checkIndexingProcessExistence();

    void dropIndex();

    void createIndex();

    StreamingResponseBody importFromArchive(MultipartFile file);

    void removeDuplicates();
}
