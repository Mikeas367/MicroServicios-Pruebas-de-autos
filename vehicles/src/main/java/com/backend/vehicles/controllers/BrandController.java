package com.backend.vehicles.controllers;

import com.backend.vehicles.exceptions.InvalidDataException;
import com.backend.vehicles.exceptions.ResourceNotFoundExeption;
import com.backend.vehicles.models.Brand;
import com.backend.vehicles.services.BrandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/brands")
public class BrandController {

    @Autowired
    private BrandService brandService;

    @GetMapping
    public List<Brand> getAllBrands() {
        return brandService.findAll();
    }

    @GetMapping("/{id}")
    public Brand getBrandById(@PathVariable int id) {
        if (brandService.findById(id) == null) {
            throw new ResourceNotFoundExeption("brand not found");
        }
        return brandService.findById(id);
    }

    @PostMapping
    public Brand createBrand(@RequestBody Brand brand) {

        if (brand.getBrandName() == null || brand.getBrandName().isEmpty()) {
            throw new InvalidDataException("the Brand name cannot be empty");
        }

        if (brandService.existsByName(brand.getBrandName())) {
            throw new InvalidDataException("the Brand name already exists");
        }

        return brandService.save(brand);
    }

    @DeleteMapping("/{id}")
    public void deleteBrand(@PathVariable int id) {
        if (brandService.findById(id) == null) {
            throw new ResourceNotFoundExeption("brand not found");
        }
        brandService.deleteById(id);
    }
}
