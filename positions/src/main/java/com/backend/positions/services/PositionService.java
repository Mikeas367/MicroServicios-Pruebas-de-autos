package com.backend.positions.services;

import com.backend.positions.exceptions.ResourceNotFoundException;
import com.backend.positions.models.Position;
import com.backend.positions.repositories.PositionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PositionService {

    @Autowired
    private PositionRepository positionRepository;

    // Metodo para que se actualizen las posiciones cada 10000 = 10seg
    @Scheduled(fixedRate = 1000000000)
    public void updatePositions() {
        List<Position> positions = positionRepository.findAll();
        for (Position position : positions) {
            // simulo que los autos se va a ir moviendo, seria como un gps que va a ir
            position.setLatitude(position.getLatitude() + 0.10);
            position.setLongitude(position.getLongitude() + 0.10);
            save(position);
        }
    }
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
