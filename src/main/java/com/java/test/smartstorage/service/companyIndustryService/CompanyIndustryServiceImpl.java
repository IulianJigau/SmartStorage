package com.java.test.smartstorage.service.companyIndustryService;

import com.java.test.smartstorage.mapper.CompanyIndustryMapper;
import com.java.test.smartstorage.model.Process;
import com.java.test.smartstorage.model.jsonMap.Company;
import com.java.test.smartstorage.model.jsonMap.companyIndustry.CompanyIndustry;
import com.java.test.smartstorage.model.jsonMap.companyIndustry.CompanyIndustryFlat;
import com.java.test.smartstorage.model.response.PaginationResponse;
import com.java.test.smartstorage.service.ImportService;
import com.java.test.smartstorage.util.Utility;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.method.annotation.StreamingResponseBody;

@Service
@RequiredArgsConstructor
public class CompanyIndustryServiceImpl implements CompanyIndustryService {

    private final CompanyIndustryMapper companyIndustryMapper;
    private final ImportService importService;

    @Override
    public void dropIndex(){
        companyIndustryMapper.dropIndex();
    }

    @Override
    public void createIndex(){
        companyIndustryMapper.createIndex();
    }

    @Override
    public StreamingResponseBody importFromArchive(MultipartFile file) {
        Process process = new Process();

        return outputStream -> {
            Utility.writeOutput("Dropping the index", outputStream);
            dropIndex();

            Utility.writeOutput("Processing files", outputStream);
            importService.importEntity(new CompanyIndustry(), file, outputStream, process);

            Utility.writeOutput("Creating the index", outputStream);
            createIndex();
        };
    }

    @Override
    public PaginationResponse<CompanyIndustryFlat> getPage(Integer page, Integer size) {
        return new PaginationResponse<>(companyIndustryMapper.getTotalEntries(), companyIndustryMapper.getPage(page, size));
    }

    @Override
    public void delete() {
        companyIndustryMapper.deleteAll();
    }
}
