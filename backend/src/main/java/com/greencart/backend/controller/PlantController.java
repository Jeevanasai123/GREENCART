package com.greencart.backend.controller;

import com.greencart.backend.model.Plant;
import com.greencart.backend.repository.PlantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/plants")
public class PlantController {

    @Autowired
    private PlantRepository plantRepository;

    @GetMapping
    public List<Plant> getAllPlants(@RequestParam(required = false) String search) {
        if (search != null && !search.isEmpty()) {
            return plantRepository.findByNameContainingIgnoreCase(search);
        }
        return plantRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Plant> getPlantById(@PathVariable Long id) {
        return plantRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public Plant createPlant(@RequestBody Plant plant) {
        return plantRepository.save(plant);
    }
}
