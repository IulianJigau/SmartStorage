package com.java.test.smartstorage.controller.QueryableController.ImportableController;

import com.java.test.smartstorage.service.queryableService.importableService.companyIndustryService.CompanyIndustryService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/companyIndustry")
public class CompanyIndustryController extends ImportableControllerAbs {

    public CompanyIndustryController(CompanyIndustryService companyIndustryService) {
        super(companyIndustryService);
    }
}
