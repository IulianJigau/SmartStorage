package com.java.test.smartstorage.service.importableClassService;

import com.java.test.smartstorage.model.response.PaginationResponse;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.method.annotation.StreamingResponseBody;

public interface ImportableClassService {
    void dropIndex();

    void createIndex();

    StreamingResponseBody importFromArchive(MultipartFile file);

    PaginationResponse<?> getPage(Integer page, Integer size);

    void delete();

    void removeDuplicates();
}
