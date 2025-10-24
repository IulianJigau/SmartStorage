package com.java.test.smartstorage.service.queryable;

import com.java.test.smartstorage.model.response.PaginationResponse;

public interface QueryableService {

    PaginationResponse<?> getPage(Integer page, Integer size);

    void delete();
}
