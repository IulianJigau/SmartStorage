package com.java.test.smartstorage.model.intermediary;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.OffsetDateTime;

@Setter
@Getter
@AllArgsConstructor
public class CompanyIndustryFlat {
    private int processId;
    private Long corporateNumber;
    private Integer businessItemId;
    private OffsetDateTime updateDate;
}
