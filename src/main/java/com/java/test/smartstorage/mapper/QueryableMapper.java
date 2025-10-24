package com.java.test.smartstorage.mapper;

import java.util.List;

public interface QueryableMapper {

    List<?> getPage(int page, int size);

    long getTotalEntries();

    void deleteAll();
}
