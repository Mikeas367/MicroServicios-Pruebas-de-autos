package com.backend.vehicles.services;

import com.backend.vehicles.exceptions.ResourceNotFoundExeption;
import com.backend.vehicles.models.Brand;
import com.backend.vehicles.repositories.BrandRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BrandService {
    @Autowired
    private BrandRepository brandRepository;

    public Brand save(Brand brand) {
        return brandRepository.save(brand);
    }

    public List<Brand> findAll() {
        return brandRepository.findAll();
    }

    public Brand findById(int id) {
        return brandRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundExeption("brand with id: " + id + " not found"));
    }
    public void deleteById(int id) {
        brandRepository.deleteById(id);
    }

    public boolean existsByName(String name) {
        return brandRepository.existsByBrandName(name);
    }
}
