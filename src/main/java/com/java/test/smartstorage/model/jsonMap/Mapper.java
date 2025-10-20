package com.java.test.smartstorage.model.jsonMap;

import java.util.List;

public interface Mapper<T, R> {

    Class<T> retrieveInitialClass();

    Class<R> retrieveFlatClass();

    List<R> flatten();

    String retrieveCopyQuery();
}
