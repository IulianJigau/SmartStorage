package com.java.test.smartstorage.controller.QueryableController.ImportableController;

import com.java.test.smartstorage.controller.QueryableController.QueryableControllerAbs;
import com.java.test.smartstorage.service.queryableService.importableService.ImportableService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.method.annotation.StreamingResponseBody;

public abstract class ImportableControllerAbs extends QueryableControllerAbs {

    private final ImportableService importableService;

    public ImportableControllerAbs(ImportableService importableService) {
        super(importableService);
        this.importableService = importableService;
    }

    @PostMapping()
    public StreamingResponseBody importFromArchive(@RequestParam MultipartFile file) {
        return importableService.importFromArchive(file);
    }
}
