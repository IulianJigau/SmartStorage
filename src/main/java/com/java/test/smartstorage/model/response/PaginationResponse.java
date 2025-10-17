package com.java.test.smartstorage.model.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class PaginationResponse<T> {
    private Long totalElements;
    private List<T> data;
}
