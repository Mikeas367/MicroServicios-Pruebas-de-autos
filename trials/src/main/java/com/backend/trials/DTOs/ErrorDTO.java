package com.backend.trials.DTOs;

import lombok.Builder;
import lombok.Data;

// este objeto lo utilizo para mandar el msj de error, se podria poner un codigo de error para tener un
// listado de los posibles errores
@Data
@Builder
public class ErrorDTO {
    private String message;
}
