package com.java.test.smartstorage.config.importSettings;

import com.java.test.smartstorage.model.identifiable.Company;
import lombok.Getter;
import lombok.Setter;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Getter
@Setter
@Configuration
public class CompanyImportSettings implements ImportSettings<Company, Company> {
    @Override
    public Class<Company> getInputClass() {
        return Company.class;
    }

    @Override
    public List<Company> transform(Company input) {
        return List.of(input);
    }

    @Override
    public Class<Company> getOutputClass() {
        return Company.class;
    }

    @Override
    public String getSqlCopyQuery() {
        return """
                    COPY company(
                        corporate_number,
                        date_of_establishment,
                        kana,
                        location,
                        name,
                        postal_code,
                        process_id,
                        qualification_grade,
                        representative_name,
                        representative_position,
                        status,
                        update_date
                    ) FROM STDIN WITH(FORMAT csv, HEADER true);
                """;
    }
}
