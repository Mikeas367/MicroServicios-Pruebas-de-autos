package com.backend.trials.services;

import com.backend.trials.exceptions.ResourceNotFoundExeption;
import com.backend.trials.models.Trial;
import com.backend.trials.repositories.TrialRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class TrialService {
    @Autowired
    private EmployeeDTOService employeeDTOService;
    @Autowired
    private InterestedDTOService interestedDTOService;
    @Autowired
    private VehicleDTOService vehicleDTOService;

    @Autowired
    private TrialRepository trialRepository;

    public List<Trial> getAll(){
        return trialRepository.findAll();
    }

    public Trial getById(int id){
        return trialRepository
                .findById(id)
                .orElseThrow(()-> new ResourceNotFoundExeption("trial not found"));
    }

    public Trial save(Trial trial){
        return trialRepository.save(trial);
    }

    public void delete(int id){
        trialRepository.deleteById(id);
    }

    public Trial startTrial(Trial trial){
        int vehicleId = trial.getVehicleId();
        employeeDTOService.findById(trial.getEmployeeId());
        interestedDTOService.getInterestedById(trial.getInterestedId());

        vehicleDTOService.findById(vehicleId);
        if (trialRepository.existsByVehicleIdAndTrialEndDateIsNull(vehicleId)){
            throw new ResourceNotFoundExeption("The Vehicle is already in use");
        }

        trial.setTrialStartDate(LocalDateTime.now());
        return trialRepository.save(trial);
    }

    public Trial endTrial(int id){
        Optional<Trial> trial = trialRepository.findById(id);

        if(trial.isPresent()){
            Trial trialFinal = trial.get();
            trialFinal.setTrialEndDate(LocalDateTime.now());
            return trialRepository.save(trialFinal);
        } else {
            throw new ResourceNotFoundExeption("trial not found");
        }
    }

    public List<Trial> getTrialsByDate(LocalDateTime date){
        return trialRepository.findByTrialStartDateAfterAndTrialEndDateIsNull(date);
    }
}
