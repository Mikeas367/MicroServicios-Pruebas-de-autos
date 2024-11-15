package com.backend.positions.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CoordinatesDTO {
    private double lat;
    private double lon;
}
