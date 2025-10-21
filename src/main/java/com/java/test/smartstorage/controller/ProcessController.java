package com.java.test.smartstorage.controller;

import com.java.test.smartstorage.model.response.PaginationResponse;
import com.java.test.smartstorage.service.processService.ProcessService;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Positive;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/process")
@RequiredArgsConstructor
public class ProcessController {

    private final ProcessService processService;

    @GetMapping()
    public PaginationResponse<?> getProcessPage(@Positive @RequestParam(defaultValue = "1") Integer page,
                                                  @Max(1000) @Positive @RequestParam(defaultValue = "50") Integer pageSize) {
        return processService.getPage(page, pageSize);
    }

    @DeleteMapping()
    public void deleteCompany() {
        processService.delete();
    }
}
