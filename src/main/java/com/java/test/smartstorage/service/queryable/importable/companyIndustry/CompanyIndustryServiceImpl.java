package com.java.test.smartstorage.service.queryable.importable.companyIndustry;

import com.java.test.smartstorage.config.importSettings.CompanyIndustryImportSettings;
import com.java.test.smartstorage.mapper.importable.CompanyIndustryMapper;
import com.java.test.smartstorage.model.identifiable.CompanyIndustry;
import com.java.test.smartstorage.model.intermediary.CompanyIndustryFlat;
import com.java.test.smartstorage.service.importJson.ImportServiceImpl;
import com.java.test.smartstorage.service.queryable.importable.ImportableServiceAbs;
import org.springframework.stereotype.Service;

@Service
public class CompanyIndustryServiceImpl extends ImportableServiceAbs<CompanyIndustry, CompanyIndustryFlat> implements CompanyIndustryService {
    public CompanyIndustryServiceImpl(CompanyIndustryMapper companyIndustryMapper, ImportServiceImpl importService, CompanyIndustryImportSettings companyIndustryImportSettings) {
        super(companyIndustryMapper, importService, companyIndustryImportSettings);
    }
}
