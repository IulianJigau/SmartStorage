package com.java.test.smartstorage.service.queryableService.importableService.companyIndustryService;

import com.java.test.smartstorage.config.importSettings.CompanyIndustryImportSettings;
import com.java.test.smartstorage.mapper.importableMapper.CompanyIndustryMapper;
import com.java.test.smartstorage.model.identifiable.CompanyIndustry;
import com.java.test.smartstorage.model.intermediary.CompanyIndustryFlat;
import com.java.test.smartstorage.service.importService.ImportServiceImpl;
import com.java.test.smartstorage.service.queryableService.importableService.ImportableServiceAbs;
import org.springframework.stereotype.Service;

@Service
public class CompanyIndustryServiceImpl extends ImportableServiceAbs<CompanyIndustry, CompanyIndustryFlat> implements CompanyIndustryService {
    public CompanyIndustryServiceImpl(CompanyIndustryMapper companyIndustryMapper, ImportServiceImpl importService, CompanyIndustryImportSettings companyIndustryImportSettings) {
        super(companyIndustryMapper, importService, companyIndustryImportSettings);
    }
}
