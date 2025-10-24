package com.java.test.smartstorage.controller.Queryable.Importable;

import com.java.test.smartstorage.service.queryable.importable.company.CompanyService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/company")
public class CompanyController extends ImportableControllerAbs {

    public CompanyController(CompanyService companyService) {
        super(companyService);
    }
}
