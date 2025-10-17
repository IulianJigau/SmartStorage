package com.java.test.smartstorage.model.intermediary;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;
import java.util.function.Function;

@AllArgsConstructor
@Getter
public class ImportSettings<T, R> {
    private Class<T> inputClass;
    private Function<T, List<R>> transformer;
    private Class<R> outputClass;
    private String sqlCopy;
}
