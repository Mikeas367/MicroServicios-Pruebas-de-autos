package com.backend.positions.services;

import com.backend.positions.dtos.CoordinatesConfigurationDTO;
import com.backend.positions.dtos.ErrorDTO;
import com.backend.positions.dtos.TrialDTO;
import com.backend.positions.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;
import java.util.List;

@Service
public class TrialDTOService {
    @Autowired
    RestTemplate restTemplate;

    public List<TrialDTO> getTrials() {
        String url = "http://localhost:8084/api/trials/actives";
        try {
            TrialDTO[] trialArray = restTemplate.getForObject(url, TrialDTO[].class); // Cambia el tipo a un arreglo
            return List.of(trialArray); // Convierte el arreglo a una lista
        } catch (HttpClientErrorException e) {
            ErrorDTO errorDTO = ErrorDTO.builder().message(e.getMessage()).build();
            throw new ResourceNotFoundException(errorDTO.getMessage());
        }
    }

}
