package com.java.test.smartstorage.service.queryable.process;

import com.java.test.smartstorage.mapper.ProcessMapper;
import com.java.test.smartstorage.service.queryable.QueryableServiceAbs;
import org.springframework.stereotype.Service;

@Service
public class ProcessServiceImpl extends QueryableServiceAbs implements ProcessService {

    private final ProcessMapper processMapper;

    public ProcessServiceImpl(ProcessMapper processMapper) {
        super(processMapper);
        this.processMapper = processMapper;
    }

    @Override
    public int create() {
        return processMapper.create();
    }

    @Override
    public void incrementProcessedFiles(int id) {
        processMapper.incrementProcessedFiles(id);
    }

    @Override
    public void updateStatus(int id, int status, String message) {
        processMapper.updateStatus(id, status, message);
    }
}
