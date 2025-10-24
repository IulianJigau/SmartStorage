package com.java.test.smartstorage.exception.controlled;

import com.java.test.smartstorage.exception.ControlledException;

public class ResourceNotFoundException extends ControlledException {
    public ResourceNotFoundException(String message) {
        super(message);
    }
}
