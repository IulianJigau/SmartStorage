package com.java.test.smartstorage.service.companyIndustryService;

import com.java.test.smartstorage.model.maps.companyIndustry.CompanyIndustryFlat;
import com.java.test.smartstorage.model.response.PaginationResponse;
import org.springframework.web.multipart.MultipartFile;

public interface CompanyIndustryService {

    void dropIndex();

    void createIndex();

    void importFromArchive(MultipartFile file);

    PaginationResponse<CompanyIndustryFlat> getPage(Integer page, Integer size);

    void delete();
}
