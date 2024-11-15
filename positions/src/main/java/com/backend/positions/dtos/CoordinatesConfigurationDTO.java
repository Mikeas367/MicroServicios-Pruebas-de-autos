package com.backend.positions.dtos;

import lombok.Data;

import java.util.List;

@Data
public class CoordinatesConfigurationDTO {
    private CoordinatesDTO agency;
    private double ratio;
    private List<RestrictedAreasDTO> restrictedAreas;
}
