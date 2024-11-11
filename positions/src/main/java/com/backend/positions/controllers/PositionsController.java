package com.backend.positions.controllers;

import com.backend.positions.exceptions.InvalidDataException;
import com.backend.positions.models.Position;
import com.backend.positions.dtos.VehicleDTO;
import com.backend.positions.services.PositionService;
import com.backend.positions.services.VehicleDTOService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

// Recibir la posición actual de un vehículo y evaluar si el vehículo se encuentra
////en una prueba para revisar si está dentro de los límites establecidos. En caso
////de que el vehículo se encuentre en una prueba y haya excedido el radio
////permitido o ingresado a una zona peligrosa, se deben disparar las acciones
////descriptas. ATENCIÓN: NO se espera que los alumnos hagan una
////notificación real a un teléfono, sino que alcanza con almacenar la notificación
////en la base de datos; pero si un grupo desea investigar e implementar una
////notificación por mail, SMS, WhatsApp o cualquier medio, tiene libertad para
////hacerlo.

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
