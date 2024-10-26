package com.backend.positions.controllers;

import com.backend.positions.models.Position;
import com.backend.positions.models.VehicleDTO;
import com.backend.positions.services.PositionService;
import com.backend.positions.services.VehicleDTOService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/positions")
public class PositionsController {

    @Autowired
    private PositionService positionService;
    @Autowired
    private VehicleDTOService vehicleDTOService;

    @PostMapping
    public Position save(@RequestBody Position position) {
        VehicleDTO vehicleDTO = vehicleDTOService.findById(position.getVehicleId());
        if (vehicleDTO != null) {
            return positionService.save(position);
        }
        else {
            throw new IllegalArgumentException("El vehiculo no puede ser nulo");
        }
    }

    @GetMapping
    public List<Position> findAll() {
        return positionService.findAll();
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable int id) {
        positionService.deleteById(id);
    }


}
