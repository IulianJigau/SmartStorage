package com.java.test.smartstorage.service.companyIndustryService;

import com.java.test.smartstorage.component.SqlQueries;
import com.java.test.smartstorage.mapper.CompanyIndustryMapper;
import com.java.test.smartstorage.model.intermediary.ImportSettings;
import com.java.test.smartstorage.model.maps.companyIndustry.CompanyIndustry;
import com.java.test.smartstorage.model.maps.companyIndustry.CompanyIndustryFlat;
import com.java.test.smartstorage.model.response.PaginationResponse;
import com.java.test.smartstorage.service.ImportService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
@RequiredArgsConstructor
public class CompanyIndustryServiceImpl implements CompanyIndustryService {

    private final SqlQueries sqlQueries;
    private final CompanyIndustryMapper companyIndustryMapper;
    private final ImportService importService;

    @Override
    public void dropIndex(){
        companyIndustryMapper.dropIndex();
    }

    @Override
    public void createIndex(){
        companyIndustryMapper.createIndex();
    }

    @Override
    public void importFromArchive(MultipartFile file) {
        ImportSettings<CompanyIndustry, CompanyIndustryFlat> importSettings =
                new ImportSettings<>(
                        CompanyIndustry.class,
                        CompanyIndustry::flatten,
                        CompanyIndustryFlat.class,
                        sqlQueries.COPY_COMPANY_INDUSTRY
                );

        dropIndex();
        importService.importEntity(importSettings, file);
        createIndex();
    }

    @Override
    public PaginationResponse<CompanyIndustryFlat> getPage(Integer page, Integer size) {
        return new PaginationResponse<>(companyIndustryMapper.getTotalEntries(), companyIndustryMapper.getPage(page, size));
    }

    @Override
    public void delete() {
        companyIndustryMapper.deleteAll();
    }
}
