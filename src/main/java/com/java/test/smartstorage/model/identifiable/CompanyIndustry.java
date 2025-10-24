package com.java.test.smartstorage.model.identifiable;

import lombok.Getter;
import lombok.Setter;

import java.time.OffsetDateTime;
import java.util.List;

@Getter
@Setter
public class CompanyIndustry implements Identifiable {
    private int processId;
    private Long corporateNumber;
    private List<Integer> businessItems;
    private OffsetDateTime updateDate;
}


