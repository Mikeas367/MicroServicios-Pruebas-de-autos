package com.backend.positions.services;

import com.backend.positions.models.VehicleDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class VehicleDTOService {
    @Autowired
    private RestTemplate restTemplate;

    public VehicleDTO findById(int id) {
        String url = "http://localhost:8080/api/vehicles/" + id;
        return restTemplate.getForObject(url, VehicleDTO.class);
    }
}
