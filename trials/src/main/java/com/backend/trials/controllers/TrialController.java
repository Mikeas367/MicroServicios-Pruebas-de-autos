package com.backend.trials.controllers;

import com.backend.trials.models.Trial;
import com.backend.trials.services.EmployeeDTOService;
import com.backend.trials.services.InterestedDTOService;
import com.backend.trials.services.TrialService;
import com.backend.trials.services.VehicleDTOService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/trials")
public class TrialController {

    @Autowired
    private TrialService trialService;
    @Autowired
    private EmployeeDTOService employeeDTOService;
    @Autowired
    private InterestedDTOService interestedDTOService;
    @Autowired
    private VehicleDTOService vehicleDTOService;

    @GetMapping
    public List<Trial> getAll() {
         return trialService.getAll();
    }

    @GetMapping("/{id}")
    public Trial getById(@PathVariable int id) {
        return trialService.getById(id);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable int id) {
        trialService.getById(id);
        trialService.delete(id);
    }

    @PostMapping
    public Trial create(@RequestBody Trial trial) {
        employeeDTOService.findById(trial.getEmployeeId());
        interestedDTOService.getInterestedById(trial.getInterestedId());
        vehicleDTOService.findById(trial.getVehicleId());
        return trialService.save(trial);
    }
}
