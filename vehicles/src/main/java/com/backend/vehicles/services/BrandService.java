package com.backend.vehicles.services;

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
        return brandRepository.findById(id).get();
    }
    public void delete(int id) {
        brandRepository.deleteById(id);
    }
}
