package com.java.test.smartstorage.config;

import com.java.test.smartstorage.component.StatusCodes;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@AutoConfiguration
@EnableConfigurationProperties(StatusCodes.class)
public class PropertiesAutoConfiguration {}
