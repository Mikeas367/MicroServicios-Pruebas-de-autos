package com.backend.vehicles.controllers;

import com.backend.vehicles.exceptions.InvalidDataException;
import com.backend.vehicles.exceptions.ResourceNotFoundExeption;
import com.backend.vehicles.models.Model;
import com.backend.vehicles.services.ModelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/models")
public class ModelController {
    @Autowired
    private ModelService modelService;

    @GetMapping
    public List<Model> getAllModels() {
        return modelService.findAll();
    }
    @GetMapping("/{id}")
    public Model getModelById(@PathVariable int id) {
        if (modelService.findById(id) == null) {
            throw new ResourceNotFoundExeption("model not found");
        }
        return modelService.findById(id);
    }

    @PostMapping
    public Model createModel(@RequestBody Model model) {
        if (model.getBrand() == null ) {
            throw new InvalidDataException("the model's brand is required");
        }
        if (model.getDescription() == null || model.getDescription().isEmpty()) {
            throw new InvalidDataException("the model's description is required");
        }
        return modelService.save(model);
    }

    @DeleteMapping("/{id}")
    public void deleteModel(@PathVariable int id) {
        if (modelService.findById(id) == null) {
            throw new ResourceNotFoundExeption("model not found");
        }
        modelService.deleteById(id);
    }
}
