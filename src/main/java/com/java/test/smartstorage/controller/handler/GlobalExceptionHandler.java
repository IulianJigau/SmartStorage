package com.java.test.smartstorage.controller.handler;

import com.java.test.smartstorage.exception.ResourceNotFoundException;
import com.java.test.smartstorage.exception.ResourceValidationException;
import com.java.test.smartstorage.model.response.ErrorResponse;
import jakarta.servlet.ServletException;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.multipart.MultipartException;

import java.util.concurrent.ExecutionException;

@Log4j2
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler({ExecutionException.class})
    public ResponseEntity<?> handleException(ExecutionException ex) {
        Throwable cause = ex.getCause();
        if(cause instanceof ResourceValidationException) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new ErrorResponse(cause.getMessage()));
        }
        else{
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ErrorResponse("Internal Server Error"));
        }
    }

    @ExceptionHandler({ResourceValidationException.class})
    public ResponseEntity<?> handleException(ResourceValidationException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(new ErrorResponse(ex.getMessage()));
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<?> handleException(ResourceNotFoundException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(new ErrorResponse(ex.getMessage()));
    }

    @ExceptionHandler(MultipartException.class)
    public ResponseEntity<?> handleException(MultipartException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(new ErrorResponse(ex.getMessage()));
    }

    @ExceptionHandler(ServletException.class)
    public ResponseEntity<?> handleException(ServletException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(new ErrorResponse(ex.getMessage()));
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> handleException(Exception ex) {
        log.error("{}\nCause: {}", ex, ex.getCause());
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(new ErrorResponse("Internal Server Error"));
    }
}
