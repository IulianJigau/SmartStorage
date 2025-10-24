package com.java.test.smartstorage.model.identifiable;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.OffsetDateTime;

@Getter
@Setter
public class Company implements Identifiable {
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
}
