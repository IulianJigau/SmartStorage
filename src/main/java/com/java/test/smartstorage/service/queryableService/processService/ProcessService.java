package com.java.test.smartstorage.service.queryableService.processService;

import com.java.test.smartstorage.service.queryableService.QueryableService;

public interface ProcessService extends QueryableService {

    int create();

    void incrementProcessedFiles(int id);

    void updateStatus(int id, int status, String message);
}
