package com.java.test.smartstorage.controller.Queryable;

import com.java.test.smartstorage.model.response.PaginationResponse;
import com.java.test.smartstorage.service.queryable.QueryableService;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Positive;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RequiredArgsConstructor
public abstract class QueryableControllerAbs {

    private final QueryableService queryableService;

    @GetMapping()
    public PaginationResponse<?> getPage(@Positive @RequestParam(defaultValue = "1") Integer page,
                                         @Max(1000) @Positive @RequestParam(defaultValue = "50") Integer pageSize) {
        return queryableService.getPage(page, pageSize);
    }

    @DeleteMapping()
    public void delete() {
        queryableService.delete();
    }
}
