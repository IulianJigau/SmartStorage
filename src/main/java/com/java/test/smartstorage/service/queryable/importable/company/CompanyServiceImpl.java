package com.java.test.smartstorage.service.queryable.importable.company;

import com.java.test.smartstorage.config.importSettings.CompanyImportSettings;
import com.java.test.smartstorage.mapper.importable.CompanyMapper;
import com.java.test.smartstorage.model.identifiable.Company;
import com.java.test.smartstorage.service.importJson.ImportServiceImpl;
import com.java.test.smartstorage.service.queryable.importable.ImportableServiceAbs;
import org.springframework.stereotype.Service;

@Service
public class CompanyServiceImpl extends ImportableServiceAbs<Company, Company> implements CompanyService {

    public CompanyServiceImpl(CompanyMapper companyMapper, ImportServiceImpl importService, CompanyImportSettings companyImportSettings) {
        super(companyMapper, importService, companyImportSettings);
    }
}
