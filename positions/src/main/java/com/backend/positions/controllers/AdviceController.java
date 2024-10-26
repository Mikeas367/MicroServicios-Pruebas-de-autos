package com.backend.positions.controllers;

import com.backend.positions.dtos.ErrorDTO;
import com.backend.positions.exceptions.InvalidDataException;
import com.backend.positions.exceptions.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

// clase que gestiona las exepciones
@RestControllerAdvice
public class AdviceController {
    @ExceptionHandler(value = InvalidDataException.class)
    public ResponseEntity<ErrorDTO> invalidDataException(RuntimeException e) {
        ErrorDTO errorDTO = ErrorDTO.builder().message(e.getMessage()).build();
        return new ResponseEntity<>(errorDTO, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = ResourceNotFoundException.class)
    public ResponseEntity<ErrorDTO> resourceNotFoundException(ResourceNotFoundException e) {
        ErrorDTO errorDTO = ErrorDTO.builder().message(e.getMessage()).build();
        return new ResponseEntity<>(errorDTO, HttpStatus.NOT_FOUND);
    }

}
