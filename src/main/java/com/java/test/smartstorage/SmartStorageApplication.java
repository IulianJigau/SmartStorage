package com.java.test.smartstorage;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.java.test.smartstorage.mapper")
public class SmartStorageApplication {

    public static void main(String[] args) {
        SpringApplication.run(SmartStorageApplication.class, args);
    }

}
