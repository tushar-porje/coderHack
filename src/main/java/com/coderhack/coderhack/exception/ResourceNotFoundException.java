package com.coderhack.coderhack.exception;

public class ResourceNotFoundException extends RuntimeException {

    String resourceName;
    String fieldName;
    String fieldValue;

    public ResourceNotFoundException( String resourceName, String fieldName, String fieldValue) {
        super(String.format("ERROR: %s not found with %s : %s", resourceName,fieldName,fieldValue));
    }
    
}
