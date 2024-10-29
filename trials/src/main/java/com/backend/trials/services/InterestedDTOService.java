package com.backend.trials.services;

import com.backend.trials.DTOs.ErrorDTO;
import com.backend.trials.DTOs.InterestedDTO;
import com.backend.trials.exceptions.ResourceNotFoundExeption;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

@Service
public class InterestedDTOService {
    @Autowired
    private RestTemplate restTemplate;

    public InterestedDTO getInterestedById(int id) {
        String url = "http://localhost:8082/api/interesteds/" + id;
        try {
            return restTemplate.getForObject(url, InterestedDTO.class);
        } catch (HttpClientErrorException e){
            ErrorDTO errorDTO = ErrorDTO.builder().message(e.getMessage()).build();
            throw new ResourceNotFoundExeption(errorDTO.getMessage());
        }
    }
}
