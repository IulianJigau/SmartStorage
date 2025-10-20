package com.java.test.smartstorage.service.companyService;

import com.java.test.smartstorage.model.jsonMap.Company;
import com.java.test.smartstorage.model.response.PaginationResponse;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.method.annotation.StreamingResponseBody;

public interface CompanyService {

    void dropIndex();

    void createIndex();

    StreamingResponseBody importFromArchive(MultipartFile file);

    PaginationResponse<Company> getPage(Integer page, Integer size);

    void delete();
}
