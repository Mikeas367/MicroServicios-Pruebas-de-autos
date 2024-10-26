package com.backend.positions.controllers;

import com.backend.positions.exceptions.InvalidDataException;
import com.backend.positions.models.Position;
import com.backend.positions.dtos.VehicleDTO;
import com.backend.positions.services.PositionService;
import com.backend.positions.services.VehicleDTOService;
import org.springframework.beans.factory.annotation.Autowired;
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
        vehicleDTOService.findById(position.getVehicleId());
        return positionService.save(position);
    }

    @GetMapping
    public List<Position> findAll() {
        return positionService.findAll();
    }

    @GetMapping("/{id}")
    public Position findById(@PathVariable int id) {
        return positionService.findById(id);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable int id) {
        positionService.findById(id);
        positionService.deleteById(id);
    }


}
