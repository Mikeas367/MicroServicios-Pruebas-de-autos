package com.backend.trials.services;

import com.backend.trials.exceptions.ResourceNotFoundExeption;
import com.backend.trials.models.Trial;
import com.backend.trials.repositories.TrialRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TrialService {
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
}
