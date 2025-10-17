package com.java.test.smartstorage.service.companyService;

import com.java.test.smartstorage.model.maps.Company;
import com.java.test.smartstorage.model.response.PaginationResponse;
import org.springframework.web.multipart.MultipartFile;

public interface CompanyService {

    void dropIndex();

    void createIndex();

    void importFromArchive(MultipartFile file);

    PaginationResponse<Company> getPage(Integer page, Integer size);

    void delete();
}
