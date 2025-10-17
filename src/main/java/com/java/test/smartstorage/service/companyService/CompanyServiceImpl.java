package com.java.test.smartstorage.service.companyService;

import com.java.test.smartstorage.component.GenericTransformer;
import com.java.test.smartstorage.component.SqlQueries;
import com.java.test.smartstorage.mapper.CompanyMapper;
import com.java.test.smartstorage.model.intermediary.ImportSettings;
import com.java.test.smartstorage.model.maps.Company;
import com.java.test.smartstorage.model.response.PaginationResponse;
import com.java.test.smartstorage.service.ImportService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
@RequiredArgsConstructor
public class CompanyServiceImpl implements CompanyService {

    private final SqlQueries sqlQueries;
    private final CompanyMapper companyMapper;
    private final ImportService importService;

    @Override
    public void dropIndex(){
        companyMapper.dropIndex();
    }

    @Override
    public void createIndex(){
        companyMapper.createIndex();
    }

    @Override
    public void importFromArchive(MultipartFile file) {
        ImportSettings<Company, Company> importSettings =
                new ImportSettings<>(
                        Company.class,
                        GenericTransformer::transform,
                        Company.class,
                        sqlQueries.COPY_COMPANY
                );

        dropIndex();
        importService.importEntity(importSettings, file);
        createIndex();
    }

    @Override
    public PaginationResponse<Company> getPage(Integer page, Integer size) {
        return new PaginationResponse<>(companyMapper.getTotalEntries(), companyMapper.getPage(page, size));
    }

    @Override
    public void delete() {
        companyMapper.deleteAll();
    }
}
