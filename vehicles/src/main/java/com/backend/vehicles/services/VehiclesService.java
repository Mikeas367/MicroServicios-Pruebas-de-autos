package com.backend.vehicles.services;

import com.backend.vehicles.exceptions.InvalidDataException;
import com.backend.vehicles.exceptions.ResourceNotFoundExeption;
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
        return vehicleRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundExeption("Vehicle with id: " + id + " not found"));
    }

    public void deleteById(int id) {
        vehicleRepository.deleteById(id);
    }

    public boolean existsByPlate(String plate) {
        return vehicleRepository.existsByplate(plate);
    }

    public Vehicle findByPlate(String plate) {
        if (!existsByPlate(plate)) {
            throw new ResourceNotFoundExeption("Vehicle with plate: " + plate + " not found");
        }
        return vehicleRepository.findVehicleByPlate(plate);
    }

    public boolean validateVehicle(Vehicle vehicle) {
        if (existsByPlate(vehicle.getPlate())) {
            throw new InvalidDataException("the plate already exists");
        }

        if (vehicle.getPlate() == null || vehicle.getPlate().isEmpty()) {
            throw new InvalidDataException("the plate cannot be empty");
        }

        if (vehicle.getModel() == null) {
            throw new InvalidDataException("The model cannot be empty");
        }
        return true;
    }

}
