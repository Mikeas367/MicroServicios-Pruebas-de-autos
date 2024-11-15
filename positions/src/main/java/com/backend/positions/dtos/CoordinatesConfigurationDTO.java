package com.backend.positions.dtos;

import lombok.Data;

import java.util.List;

@Data
public class CoordinatesConfigurationDTO {
    private CoordinatesDTO coordenadasAgencia;
    private double radioAdmitidoKm;
    private List<RestrictedAreasDTO> zonasRestringidas;
}
