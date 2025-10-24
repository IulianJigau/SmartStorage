package com.java.test.smartstorage.controller.QueryableController;

import com.java.test.smartstorage.service.queryableService.processService.ProcessService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/process")
public class ProcessController extends QueryableControllerAbs {
    public ProcessController(ProcessService processService) {
        super(processService);
    }
}
