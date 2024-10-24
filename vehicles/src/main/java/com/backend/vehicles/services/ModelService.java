package com.backend.vehicles.services;

import com.backend.vehicles.models.Model;
import com.backend.vehicles.repositories.ModelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ModelService {
    @Autowired
    private ModelRepository modelRepository;

    public Model save(Model model) {
        return modelRepository.save(model);
    }

    public List<Model> findAll() {
        return modelRepository.findAll();
    }

    public Model findById(int id) {
        return modelRepository.findById(id).get();
    }

    public void delete(int id) {
        modelRepository.deleteById(id);
    }

}
