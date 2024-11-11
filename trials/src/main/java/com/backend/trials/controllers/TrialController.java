package com.backend.trials.controllers;

import com.backend.trials.models.Trial;
import com.backend.trials.services.TrialService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/trials")
public class TrialController {

    @Autowired
    private TrialService trialService;

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

    // endpoint para comenzar una prueba
    @PostMapping("/start")
    public Trial startTrial(@RequestBody Trial trial) {
        return trialService.startTrial(trial);
    }

    @PutMapping("/end/{id}")
    public Trial endTrial(@PathVariable int id) {
        return trialService.endTrial(id);
    }
    // VERIFICAR QUE FUNCIONE
    @GetMapping("/active-since/{date}")
    public List<Trial> getActiveSinceDate(
            @PathVariable("date")
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
            LocalDateTime date) {
        // se parsea la fecha que me dan como path variable
        System.out.println("FECHA =>" + date);
        return trialService.getTrialsByDate(date);
    }


}
