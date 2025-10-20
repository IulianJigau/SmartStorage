package com.java.test.smartstorage.model.jsonMap;

import lombok.Getter;
import lombok.Setter;

import java.time.OffsetDateTime;

@Getter
@Setter
public class Process {
    private int id;
    private int status;
    private int filesProcessed;
    private String message;
    private OffsetDateTime createdAt;
    private OffsetDateTime updatedAt;
}
