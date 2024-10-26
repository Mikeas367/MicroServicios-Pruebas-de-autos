package com.backend.vehicles.services;

import com.backend.vehicles.models.Vehicle;
import com.backend.vehicles.repositories.VehicleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VehiclesService {

    @Autowired
    private VehicleRepository vehicleRepository;

    public Vehicle save(Vehicle vehicle) {
           return vehicleRepository.save(vehicle);
    }

    public List<Vehicle> findAll() {
        return vehicleRepository.findAll();
    }

    public Vehicle findById(int id) {
        return vehicleRepository.findById(id).get();
    }

    public void deleteById(int id) {
        vehicleRepository.deleteById(id);
    }

    public boolean existsByPlate(String plate) {
        return vehicleRepository.existsByplate(plate);
    }

}
