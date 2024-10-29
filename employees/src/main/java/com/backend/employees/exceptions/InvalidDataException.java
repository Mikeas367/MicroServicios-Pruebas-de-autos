package com.backend.employees.exceptions;

import lombok.Data;

@Data
public class InvalidDataException extends RuntimeException {
    private String message;

    public InvalidDataException(String message) {
        super(message);
        this.message = message;
    }
}
