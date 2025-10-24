package com.java.test.smartstorage.model.intermediary;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.io.InputStream;

@Getter
@AllArgsConstructor
public class OpenedFile {
    private String name;
    private InputStream inputStream;
}
