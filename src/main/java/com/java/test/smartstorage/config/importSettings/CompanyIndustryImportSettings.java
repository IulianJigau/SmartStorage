package com.java.test.smartstorage.config.importSettings;

import com.java.test.smartstorage.model.identifiable.CompanyIndustry;
import com.java.test.smartstorage.model.intermediary.CompanyIndustryFlat;
import lombok.Getter;
import lombok.Setter;
import org.springframework.context.annotation.Configuration;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
@Configuration
public class CompanyIndustryImportSettings implements ImportSettings<CompanyIndustry, CompanyIndustryFlat> {
    @Override
    public Class<CompanyIndustry> getInputClass() {
        return CompanyIndustry.class;
    }

    @Override
    public List<CompanyIndustryFlat> transform(CompanyIndustry input) {
        if (input.getBusinessItems() == null) return null;
        return input.getBusinessItems().stream()
                .map(businessItemId -> new CompanyIndustryFlat(
                        input.getProcessId(),
                        input.getCorporateNumber(),
                        businessItemId,
                        input.getUpdateDate()))
                .collect(Collectors.toList());
    }

    @Override
    public Class<CompanyIndustryFlat> getOutputClass() {
        return CompanyIndustryFlat.class;
    }

    @Override
    public String getSqlCopyQuery() {
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



