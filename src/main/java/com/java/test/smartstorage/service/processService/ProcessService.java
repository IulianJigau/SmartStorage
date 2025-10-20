package com.java.test.smartstorage.service.processService;

import com.java.test.smartstorage.model.Process;
import com.java.test.smartstorage.model.response.PaginationResponse;

public interface ProcessService {

    int create();

    PaginationResponse<Process> getPage(Integer page, Integer size);

    void incrementProcessedFiles(int id);

    void updateStatus(int id, int status, String message);

    void delete();
}
