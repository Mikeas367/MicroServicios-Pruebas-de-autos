package com.backend.positions.exceptions;

import lombok.Data;

@Data
public class ResourceNotFoundException extends RuntimeException{
    private String message;

    public ResourceNotFoundException(String message) {
        super(message);
        this.message = message;
    }
}
