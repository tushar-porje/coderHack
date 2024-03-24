package com.coderhack.coderhack.exception;

public class ResourceAlreadyExistException extends RuntimeException{
    
    String resourceName;
    String fieldName;
    String fieldValue;

    public ResourceAlreadyExistException( String resourceName, String fieldName, String fieldValue) {
        super(String.format("ERROR: %s with %s : %s already exists", resourceName,fieldName,fieldValue));
    }
}
