package com.java.test.smartstorage.service.companyService;

import com.java.test.smartstorage.mapper.CompanyMapper;
import com.java.test.smartstorage.model.jsonMap.Company;
import com.java.test.smartstorage.model.response.PaginationResponse;
import com.java.test.smartstorage.service.ImportService;
import com.java.test.smartstorage.util.Utility;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.method.annotation.StreamingResponseBody;

@Service
@RequiredArgsConstructor
public class CompanyServiceImpl implements CompanyService {

    private final CompanyMapper companyMapper;
    private final ImportService importService;

    @Override
    public void dropIndex() {
        companyMapper.dropIndex();
    }

    @Override
    public void createIndex() {
        companyMapper.createIndex();
    }

    @Override
    public StreamingResponseBody importFromArchive(MultipartFile file) {
        return outputStream -> {
            Utility.writeOutput("Dropping the index", outputStream);
            dropIndex();

            Utility.writeOutput("Processing files", outputStream);
            importService.importEntity(new Company(), file, outputStream);

            Utility.writeOutput("Creating the index", outputStream);
            createIndex();
        };
    }

    @Override
    public PaginationResponse<Company> getPage(Integer page, Integer size) {
        return new PaginationResponse<>(companyMapper.getTotalEntries(), companyMapper.getPage(page, size));
    }

    @Override
    public void delete() {
        companyMapper.deleteAll();
    }
}
