package com.java.test.smartstorage.component;

import org.springframework.context.annotation.Configuration;

@Configuration
public class SqlQueries {
    public final String COPY_COMPANY = """
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

    public final String COPY_COMPANY_INDUSTRY = """
                COPY company_industry(
                    business_item_id,
                    corporate_number,
                    process_id
                ) FROM STDIN WITH(FORMAT csv, HEADER true);
            """;
}
