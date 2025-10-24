package com.java.test.smartstorage.util;

import com.java.test.smartstorage.exception.controlledException.ResourceValidationException;
import org.springframework.web.context.request.async.AsyncRequestNotUsableException;

import java.io.IOException;
import java.io.OutputStream;
import java.sql.SQLException;

public class Utility {
    private static final String SQL_CONFLICT_CODE = "23505";

    public static void checkSQLExceptionCode(SQLException e) {
        switch (e.getSQLState()) {
            case SQL_CONFLICT_CODE:
                throw new ResourceValidationException("Duplicate keys detected. Cannot proceed.");
            default:
                throw new RuntimeException("Failed to add the data to the database", e);
        }
    }

    public static void writeOutput(String message, OutputStream outputStream) {
        try {
            outputStream.write((message + "\n").getBytes());
            outputStream.flush();
        } catch (AsyncRequestNotUsableException e) {
            throw new RuntimeException("Output stream was closed prematurely");
        } catch (IOException e) {
            throw new RuntimeException("An exception occurred while writing to the output stream");
        }
    }
}
