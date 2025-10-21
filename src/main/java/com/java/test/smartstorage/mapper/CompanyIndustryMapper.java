package com.java.test.smartstorage.mapper;

import com.java.test.smartstorage.model.jsonMap.companyIndustry.CompanyIndustryFlat;

import java.util.List;

public interface CompanyIndustryMapper {

    void dropIndex();

    void createIndex();

    List<CompanyIndustryFlat> getPage(int page, int size);

    long getTotalEntries();

    void deleteAll();

    void removeDuplicates();
}
