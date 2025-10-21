package com.java.test.smartstorage.model.jsonMap.companyIndustry;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.OffsetDateTime;

@AllArgsConstructor
@Setter
@Getter
public class CompanyIndustryFlat {
    private int processId;
    private Long corporateNumber;
    private Integer businessItemId;
    private OffsetDateTime updateDate;
}
