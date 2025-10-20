package com.java.test.smartstorage.service.processService;

import com.java.test.smartstorage.mapper.ProcessMapper;
import com.java.test.smartstorage.model.jsonMap.Process;
import com.java.test.smartstorage.model.response.PaginationResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProcessServiceImpl implements ProcessService {

    private final ProcessMapper processMapper;

    @Override
    public int create() {
        return processMapper.create();
    }

    @Override
    public PaginationResponse<Process> getPage(Integer page, Integer size) {
        return new PaginationResponse<>(processMapper.getTotalEntries(), processMapper.getPage(page, size));
    }

    @Override
    public void incrementProcessedFiles(int id) {
        processMapper.incrementProcessedFiles(id);
    }

    @Override
    public void updateStatus(int id, int status, String message) {
        processMapper.updateStatus(id, status, message);
    }

    @Override
    public void delete() {
        processMapper.deleteAll();
    }
}
