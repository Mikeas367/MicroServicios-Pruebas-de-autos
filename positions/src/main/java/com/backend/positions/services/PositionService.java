package com.backend.positions.services;

import com.backend.positions.exceptions.ResourceNotFoundException;
import com.backend.positions.models.Position;
import com.backend.positions.repositories.PositionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PositionService {

    @Autowired
    private PositionRepository positionRepository;

    public List<Position> findAll() {
        return positionRepository.findAll();
    }

    public Position findById(int id) {

        return positionRepository
                .findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("position not found"));
    }
    public Position save(Position position) {
        return positionRepository.save(position);
    }
    public void deleteById(int id) {
        positionRepository.deleteById(id);
    }
}
