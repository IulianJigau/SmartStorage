package com.java.test.smartstorage.service.companyIndustryService;

import com.java.test.smartstorage.model.jsonMap.companyIndustry.CompanyIndustryFlat;
import com.java.test.smartstorage.model.response.PaginationResponse;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.method.annotation.StreamingResponseBody;

public interface CompanyIndustryService {

    void dropIndex();

    void createIndex();

    StreamingResponseBody importFromArchive(MultipartFile file);

    PaginationResponse<CompanyIndustryFlat> getPage(Integer page, Integer size);

    void delete();
}
