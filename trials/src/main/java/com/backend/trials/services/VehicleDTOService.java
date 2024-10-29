package com.backend.trials.services;

import com.backend.trials.DTOs.ErrorDTO;
import com.backend.trials.DTOs.VehicleDTO;
import com.backend.trials.exceptions.ResourceNotFoundExeption;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

@Service
public class VehicleDTOService {
    @Autowired
    private RestTemplate restTemplate;

    public VehicleDTO findById(int id) {
        String url = "http://localhost:8080/vehicles/" + id;
        try {
            return restTemplate.getForObject(url, VehicleDTO.class);
        } catch (HttpClientErrorException e) {
            ErrorDTO errorDTO = ErrorDTO.builder().message(e.getMessage()).build();
            throw new ResourceNotFoundExeption(e.getMessage());
        }
    }
}
