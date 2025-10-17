package com.java.test.smartstorage.model.maps.companyIndustry;

import com.java.test.smartstorage.model.maps.Identifiable;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
public class CompanyIndustry implements Identifiable {
    private int processId;
    private Long corporateNumber;
    private List<Integer> businessItems;

    public static List<CompanyIndustryFlat> flatten(CompanyIndustry companyIndustry) {
        if (companyIndustry.businessItems == null) return null;
        return companyIndustry.businessItems.stream()
                .map(businessItemId -> new CompanyIndustryFlat(
                        companyIndustry.processId,
                        companyIndustry.corporateNumber,
                        businessItemId)
                ).collect(Collectors.toList());
    }
}


