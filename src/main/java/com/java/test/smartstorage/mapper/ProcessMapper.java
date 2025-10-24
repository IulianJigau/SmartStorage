package com.java.test.smartstorage.mapper;

public interface ProcessMapper extends QueryableMapper {

    int create();

    void incrementProcessedFiles(int id);

    void updateStatus(int id, int status, String message);
}
