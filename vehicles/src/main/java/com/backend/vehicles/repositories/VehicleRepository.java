package com.backend.vehicles.repositories;

import com.backend.vehicles.models.Vehicle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VehicleRepository extends JpaRepository<Vehicle, Integer> {
    Vehicle findVehicleByPlate(String plate);
    boolean existsByplate(String plate);
}
