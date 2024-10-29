package com.backend.trials.services;

import com.backend.trials.DTOs.EmployeeDTO;
import com.backend.trials.DTOs.ErrorDTO;
import com.backend.trials.exceptions.ResourceNotFoundExeption;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

@Service
public class EmployeeDTOService {
    @Autowired
    private RestTemplate restTemplate;

    public EmployeeDTO findById(int id) {
        String url = "http://localhost:8083/api/employees/" + id;
        try {
            return restTemplate.getForObject(url, EmployeeDTO.class);
        } catch (HttpClientErrorException e) {
            ErrorDTO errorDTO = ErrorDTO.builder().message(e.getMessage()).build();
            throw new ResourceNotFoundExeption(errorDTO.getMessage());
        }
    }
}
