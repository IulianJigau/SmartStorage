package com.java.test.smartstorage.service.queryable;

import com.java.test.smartstorage.mapper.QueryableMapper;
import com.java.test.smartstorage.model.response.PaginationResponse;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public abstract class QueryableServiceAbs implements QueryableService {
    private final QueryableMapper queryableMapper;

    @Override
    public PaginationResponse<?> getPage(Integer page, Integer size) {
        return new PaginationResponse<>(queryableMapper.getTotalEntries(), queryableMapper.getPage(page, size));
    }

    @Override
    public void delete() {
        queryableMapper.deleteAll();
    }
}
