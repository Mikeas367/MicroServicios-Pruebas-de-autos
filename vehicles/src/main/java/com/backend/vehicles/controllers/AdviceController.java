package com.backend.vehicles.controllers;

import com.backend.vehicles.DTOs.ErrorDTO;
import com.backend.vehicles.exceptions.InvalidDataException;
import com.backend.vehicles.exceptions.ResourceNotFoundExeption;
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

    @ExceptionHandler(value = ResourceNotFoundExeption.class)
    public ResponseEntity<ErrorDTO> resourceNotFoundException(ResourceNotFoundExeption e) {
        ErrorDTO errorDTO = ErrorDTO.builder().message(e.getMessage()).build();
        return new ResponseEntity<>(errorDTO, HttpStatus.NOT_FOUND);
    }

}
