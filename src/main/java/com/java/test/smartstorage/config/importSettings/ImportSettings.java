package com.java.test.smartstorage.config.importSettings;

import com.java.test.smartstorage.model.identifiable.Identifiable;

import java.util.List;

public interface ImportSettings<T extends Identifiable, R> {

    Class<T> getInputClass();

    List<R> transform(T input);

    Class<R> getOutputClass();

    String getSqlCopyQuery();
}