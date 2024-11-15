package com.backend.positions.dtos;

import lombok.Data;

@Data
public class RestrictedAreasDTO {
    private CoordinatesDTO noroeste;
    private CoordinatesDTO sureste;
}
