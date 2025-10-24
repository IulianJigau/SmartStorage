package com.java.test.smartstorage.mapper.importableMapper;

import com.java.test.smartstorage.mapper.QueryableMapper;

public interface ImportableMapper extends QueryableMapper {

    boolean checkIndexingProcessExistence();

    void dropIndex();

    void createIndex();

    void removeDuplicates();
}
