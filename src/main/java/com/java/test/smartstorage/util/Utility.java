package com.java.test.smartstorage.util;

import java.io.IOException;
import java.io.OutputStream;

public class Utility {

    private static int counter;

    public static int getCounter() {
        return ++counter;
    }

    public static void resetCounter() {
        counter = 0;
    }

    public static void writeOutput(String message, OutputStream outputStream) {
        try{
            outputStream.write((message + "\n").getBytes());
            outputStream.flush();
        }
        catch (IOException e){
            throw new RuntimeException("Failed to write to output stream", e);
        }
    }
}
