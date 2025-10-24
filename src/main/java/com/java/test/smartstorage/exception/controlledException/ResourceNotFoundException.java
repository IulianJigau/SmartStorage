package com.java.test.smartstorage.exception.controlledException;

import com.java.test.smartstorage.exception.ControlledException;

public class ResourceNotFoundException extends ControlledException {
    public ResourceNotFoundException(String message) {
        super(message);
    }
}
