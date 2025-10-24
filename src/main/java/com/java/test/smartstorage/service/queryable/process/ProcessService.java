package com.java.test.smartstorage.service.queryable.process;

import com.java.test.smartstorage.service.queryable.QueryableService;

public interface ProcessService extends QueryableService {

    int create();

    void incrementProcessedFiles(int id);

    void updateStatus(int id, int status, String message);
}
