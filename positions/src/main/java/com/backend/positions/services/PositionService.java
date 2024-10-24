package com.backend.positions.services;

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
        return positionRepository.findById(id).get();
    }
    public Position save(Position position) {
        return positionRepository.save(position);
    }
    public void delete(Position position) {
        positionRepository.delete(position);
    }
}
