package com.backend.interesteds.exceptions;

import lombok.Data;

@Data
public class ResourceNotFoundExeption extends RuntimeException{
    private String message;

    public ResourceNotFoundExeption(String message) {
        super(message);
        this.message = message;
    }
}
