package com.java.test.smartstorage.component;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "app.status-code")
@Setter
@Getter
public class StatusCodes {
    private int processing;
    private int deduplicating;
    private int completed;
    private int failed;
}
