package com.java.test.smartstorage.service.queryableService.importableService.companyService;

import com.java.test.smartstorage.config.importSettings.CompanyImportSettings;
import com.java.test.smartstorage.mapper.importableMapper.CompanyMapper;
import com.java.test.smartstorage.model.identifiable.Company;
import com.java.test.smartstorage.service.ImportService;
import com.java.test.smartstorage.service.queryableService.importableService.ImportableServiceAbs;
import org.springframework.stereotype.Service;

@Service
public class CompanyServiceImpl extends ImportableServiceAbs<Company, Company> implements CompanyService {

    public CompanyServiceImpl(CompanyMapper companyMapper, ImportService importService, CompanyImportSettings companyImportSettings) {
        super(companyMapper, importService, companyImportSettings);
    }
}
