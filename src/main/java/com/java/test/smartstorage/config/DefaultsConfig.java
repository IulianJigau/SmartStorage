package com.java.test.smartstorage.config;

import com.java.test.smartstorage.component.StatusCodes;
import com.java.test.smartstorage.model.Process;
import com.java.test.smartstorage.service.queryable.process.ProcessService;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class DefaultsConfig {

    private final ProcessService processService;
    private final StatusCodes statusCodes;

    public void configureProcess() {
        Process.configure(processService, statusCodes);
    }

    @PostConstruct
    public void init() {
        configureProcess();
    }
}
