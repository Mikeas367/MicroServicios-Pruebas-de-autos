package com.backend.interesteds.controllers;

import com.backend.interesteds.exceptions.InvalidDataException;
import com.backend.interesteds.models.Interested;
import com.backend.interesteds.services.InterestedService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/interesteds")
public class InterestedController {
    @Autowired
    private InterestedService interestedService;

    @GetMapping
    public List<Interested> getAll() {
        return interestedService.findAll();
    }

    @GetMapping("/{id}")
    public Interested getById(@PathVariable int id) {
        return interestedService.getById(id);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable int id) {
        interestedService.getById(id);
        interestedService.delete(id);
    }

    @PostMapping
    public Interested add(@RequestBody Interested i) {
        // ----> Metodos valido los datos ----> Es buena practica hacerlos en el service????
        interestedService.validateRequiredFields(i);
        interestedService.validateLicense(i);
        // aca valido si el documento y el tipo de documento ya existian antes
        interestedService.validateDocument(i);

        return interestedService.save(i);
    }
}
