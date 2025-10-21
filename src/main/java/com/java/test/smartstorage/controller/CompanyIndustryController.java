package com.java.test.smartstorage.controller;

import com.java.test.smartstorage.model.response.PaginationResponse;
import com.java.test.smartstorage.service.importableClassService.companyIndustryService.CompanyIndustryService;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Positive;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.method.annotation.StreamingResponseBody;

@RestController
@RequestMapping("/companyIndustry")
@RequiredArgsConstructor
public class CompanyIndustryController {

    private final CompanyIndustryService companyIndustryService;

    @PostMapping()
    public StreamingResponseBody importCompany(@RequestParam MultipartFile file) {
        return companyIndustryService.importFromArchive(file);
    }

    @GetMapping()
    public PaginationResponse<?> getCompanyIndustryPage(@Positive @RequestParam(defaultValue = "1") Integer page,
                                                  @Max(1000) @Positive @RequestParam(defaultValue = "50") Integer pageSize
    ) {
        return companyIndustryService.getPage(page, pageSize);
    }

    @DeleteMapping()
    public void deleteCompanyIndustry() {
        companyIndustryService.delete();
    }
}
