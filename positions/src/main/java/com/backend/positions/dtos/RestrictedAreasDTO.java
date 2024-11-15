package com.backend.positions.dtos;

import lombok.Data;

@Data
public class RestrictedAreasDTO {
    private CoordinatesDTO northwest;
    private CoordinatesDTO southeast;
}
