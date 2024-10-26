package com.backend.positions.services;

import com.backend.positions.dtos.ErrorDTO;
import com.backend.positions.dtos.VehicleDTO;
import com.backend.positions.exceptions.ResourceNotFoundException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

@Service
public class VehicleDTOService {
    @Autowired
    private RestTemplate restTemplate;

    public VehicleDTO findById(int id) {
        String url = "http://localhost:8080/api/vehicles/" + id;
        try {
            return restTemplate.getForObject(url, VehicleDTO.class);
        } catch (HttpClientErrorException e) {
            ErrorDTO errorDTO = ErrorDTO.builder().message(e.getMessage()).build();
            throw new ResourceNotFoundException(errorDTO.getMessage());
        }
    }
}
