package com.java.test.smartstorage.mapper;

import com.java.test.smartstorage.model.maps.Process;

import java.util.List;

public interface ProcessMapper {

    int create();

    List<Process> getPage(int page, int size);

    long getTotalEntries();

    void incrementProcessedFiles(int id);

    void updateStatus(int id, int status, String message);

    void deleteAll();
}
