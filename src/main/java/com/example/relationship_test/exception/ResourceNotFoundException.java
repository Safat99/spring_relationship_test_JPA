package com.example.relationship_test.exception;
public class ResourceNotFoundException extends RuntimeException {
    private static final long SerialVersionUID = 1L;
    public ResourceNotFoundException(String msg){
        super(msg);
    }
}
