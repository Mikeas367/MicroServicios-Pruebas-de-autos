package com.backend.positions.services;

import com.backend.positions.dtos.CoordinatesConfigurationDTO;
import com.backend.positions.dtos.ErrorDTO;
import com.backend.positions.dtos.VehicleDTO;
import com.backend.positions.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import javax.print.DocFlavor;

@Service
public class CoordinatesConfigurationDTOService {
    @Autowired
    RestTemplate restTemplate;

    public CoordinatesConfigurationDTO getCoordinatesConfigurationDTO() {
        String url = "https://labsys.frc.utn.edu.ar/apps-disponibilizadas/backend/api/v1/configuracion/";
        try {
            return restTemplate.getForObject(url, CoordinatesConfigurationDTO.class);
        } catch (HttpClientErrorException e) {
            ErrorDTO errorDTO = ErrorDTO.builder().message(e.getMessage()).build();
            throw new ResourceNotFoundException(errorDTO.getMessage());
        }
    }
}
