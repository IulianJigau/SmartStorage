package com.java.test.smartstorage.controller;

import com.java.test.smartstorage.model.response.PaginationResponse;
import com.java.test.smartstorage.service.companyService.CompanyService;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Positive;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.method.annotation.StreamingResponseBody;

@RestController
@RequestMapping("/company")
@RequiredArgsConstructor
public class CompanyController {

    private final CompanyService companyService;

    @PostMapping()
    public StreamingResponseBody importCompany(@RequestParam MultipartFile file) {
        return companyService.importFromArchive(file);
    }

    @GetMapping()
    public PaginationResponse<?> getCompanyPage(@Positive @RequestParam(defaultValue = "1") Integer page,
                                                  @Max(1000) @Positive @RequestParam(defaultValue = "50") Integer pageSize) {
        return companyService.getPage(page, pageSize);
    }

    @DeleteMapping()
    public void deleteCompany() {
        companyService.delete();
    }
}
