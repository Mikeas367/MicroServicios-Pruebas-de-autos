package com.backend.vehicles.controllers;

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
        return brandService.findById(id);
    }

    @PostMapping
    public Brand createBrand(@RequestBody Brand brand) {
        return brandService.save(brand);
    }

    @DeleteMapping("/{id}")
    public void deleteBrand(@PathVariable int id) {
        brandService.delete(id);
    }
}
