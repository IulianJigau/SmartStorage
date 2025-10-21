package com.java.test.smartstorage.model;

import com.java.test.smartstorage.component.StatusCodes;
import com.java.test.smartstorage.service.processService.ProcessService;
import lombok.Getter;
import lombok.Setter;

import java.time.OffsetDateTime;

@Getter
@Setter
public class Process {
    private static ProcessService processService;
    private static StatusCodes statusCodes;

    private int id;
    private int status;
    private int filesProcessed;
    private String message;
    private OffsetDateTime createdAt;
    private OffsetDateTime updatedAt;

    public static void configure(ProcessService processService, StatusCodes statusCodes) {
        Process.processService = processService;
        Process.statusCodes = statusCodes;
    }

    public Process initialize() {
        id = processService.create();
        return this;
    }

    public void incrementProcessedFiles() {
        processService.incrementProcessedFiles(id);
    }

    public void setDeduplicating() {
        processService.updateStatus(id, statusCodes.getFailed(), null);
    }

    public void setFailed(String message) {
        processService.updateStatus(id, statusCodes.getFailed(), message);
    }

    public void setCompleted(String message) {
        processService.updateStatus(id, statusCodes.getCompleted(), message);
    }
}
