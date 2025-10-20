package com.java.test.smartstorage.mapper;

import com.java.test.smartstorage.model.jsonMap.Company;

import java.util.List;

public interface CompanyMapper {

    void dropIndex();

    void createIndex();

    List<Company> getPage(int page, int size);

    long getTotalEntries();

    void deleteAll();
}
