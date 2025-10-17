package com.java.test.smartstorage.component;

import java.util.List;

public class GenericTransformer {
    public static <R> List<R> transform(R object) {
        return List.of(object);
    }
}
