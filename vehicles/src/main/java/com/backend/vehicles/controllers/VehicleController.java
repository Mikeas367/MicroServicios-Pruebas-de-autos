package com.backend.vehicles.controllers;

import com.backend.vehicles.models.Vehicle;
import com.backend.vehicles.repositories.VehicleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/vehicles")
public class VehicleController {
    @Autowired
    private VehicleRepository vehicleRepository;

    @GetMapping
    public List<Vehicle> findAll() {
        return vehicleRepository.findAll();
    }

    @GetMapping("/{id}")
    public Vehicle findById(@PathVariable int id) {
        return vehicleRepository.findById(id).get();
    }

    @PostMapping
    public Vehicle save(@RequestBody Vehicle vehicle) {
        return vehicleRepository.save(vehicle);
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable int id) {
        vehicleRepository.deleteById(id);
    }
}
