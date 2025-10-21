package com.java.test.smartstorage.model.jsonMap.companyIndustry;

import com.java.test.smartstorage.model.jsonMap.Identifiable;
import com.java.test.smartstorage.model.jsonMap.Mapper;
import lombok.Getter;
import lombok.Setter;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
public class CompanyIndustry implements Identifiable, Mapper<CompanyIndustry, CompanyIndustryFlat> {
    private int processId;
    private Long corporateNumber;
    private List<Integer> businessItems;
    private OffsetDateTime updateDate;

    @Override
    public Class<CompanyIndustry> retrieveInitialClass() {
        return CompanyIndustry.class;
    }

    @Override
    public Class<CompanyIndustryFlat> retrieveFlatClass() {
        return CompanyIndustryFlat.class;
    }

    @Override
    public List<CompanyIndustryFlat> flatten() {
        if (businessItems == null) return null;
        return businessItems.stream()
                .map(businessItemId -> new CompanyIndustryFlat(processId, corporateNumber, businessItemId, updateDate))
                .collect(Collectors.toList());
    }

    @Override
    public String retrieveCopyQuery() {
        return """
                    COPY company_industry(
                        business_item_id,
                        corporate_number,
                        process_id,
                        update_date
                    ) FROM STDIN WITH(FORMAT csv, HEADER true);
                """;
    }
}


