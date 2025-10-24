package com.java.test.smartstorage.controller.QueryableController.ImportableController;

import com.java.test.smartstorage.service.queryableService.importableService.companyService.CompanyService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/company")
public class CompanyController extends ImportableControllerAbs {

    public CompanyController(CompanyService companyService) {
        super(companyService);
    }
}
