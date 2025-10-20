package com.java.test.smartstorage.model.jsonMap;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.OffsetDateTime;
import java.util.List;

@Getter
@Setter
public class Company implements Identifiable, Mapper<Company, Company> {
    private int processId;
    private Long corporateNumber;
    private String representativePosition;
    private String representativeName;
    private Integer postalCode;
    private String location;
    private String name;
    private String kana;
    private String status;
    private String qualificationGrade;
    private OffsetDateTime updateDate;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate dateOfEstablishment;

    @Override
    public Class<Company> retrieveInitialClass() {
        return Company.class;
    }

    @Override
    public Class<Company> retrieveFlatClass() {
        return Company.class;
    }

    @Override
    public List<Company> flatten() {
        return List.of(this);
    }

    @Override
    public String retrieveCopyQuery() {
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
