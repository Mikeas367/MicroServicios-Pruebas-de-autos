package com.backend.vehicles.controllers;

import com.backend.vehicles.exceptions.InvalidDataException;
import com.backend.vehicles.exceptions.ResourceNotFoundExeption;
import com.backend.vehicles.models.Vehicle;
import com.backend.vehicles.repositories.VehicleRepository;
import com.backend.vehicles.services.VehiclesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/vehicles")
public class VehicleController {
    @Autowired
    private VehiclesService vehiclesService;

    @GetMapping
    public List<Vehicle> findAll() {
        return vehiclesService.findAll();
    }

    @GetMapping("/{id}")
    public Vehicle findById(@PathVariable int id) {
        return vehiclesService.findById(id);
    }


    @PostMapping
    public Vehicle save(@RequestBody Vehicle vehicle) {

        if (vehiclesService.existsByPlate(vehicle.getPlate())) {
            throw new InvalidDataException("the plate already exists");
        }

        if (vehicle.getPlate() == null || vehicle.getPlate().isEmpty()) {
            throw new InvalidDataException("the plate cannot be empty");
        }

        if (vehicle.getModel() == null) {
            throw new InvalidDataException("The model cannot be empty");
        }

        return vehiclesService.save(vehicle);
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable int id) {
        vehiclesService.findById(id);
        vehiclesService.deleteById(id);
    }

}
