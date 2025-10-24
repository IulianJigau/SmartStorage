package com.java.test.smartstorage.controller.Queryable.Importable;

import com.java.test.smartstorage.service.queryable.importable.companyIndustry.CompanyIndustryService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/companyIndustry")
public class CompanyIndustryController extends ImportableControllerAbs {

    public CompanyIndustryController(CompanyIndustryService companyIndustryService) {
        super(companyIndustryService);
    }
}
