package com.backend.positions.services;

import com.backend.positions.dtos.CoordinatesConfigurationDTO;
import com.backend.positions.dtos.CoordinatesDTO;
import com.backend.positions.dtos.RestrictedAreasDTO;
import com.backend.positions.exceptions.ResourceNotFoundException;
import com.backend.positions.models.Notification;
import com.backend.positions.models.Position;
import com.backend.positions.repositories.NotificationRepository;
import com.backend.positions.repositories.PositionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class PositionService {

    @Autowired
    private PositionRepository positionRepository;

    @Autowired
    private NotificationRepository notificationRepository;

    @Autowired
    private CoordinatesConfigurationDTOService coordinatesService;

    @Scheduled(fixedRate = 10000) // Cada 10 segundos
    public void updatePositions() {
        List<Position> positions = positionRepository.findAll();
        CoordinatesConfigurationDTO configuration = coordinatesService.getCoordinatesConfigurationDTO();

        for (Position position : positions) {
            // Simular movimiento del vehículo
            position.setLatitude(position.getLatitude() + 0.10);
            position.setLongitude(position.getLongitude() + 0.10);
            save(position);

            // Verificar si está en una zona restringida
            if (isInRestrictedArea(position, configuration.getRestrictedAreas())) {
                Notification notification = new Notification();
                notification.setVehicleId(position.getVehicleId());
                notification.setMessage("El vehículo está en una zona restringida.");
                notification.setTimestamp(LocalDateTime.now());
                notificationRepository.save(notification);
            }
        }
    }

    private boolean isInRestrictedArea(Position position, List<RestrictedAreasDTO> restrictedAreas) {
        for (RestrictedAreasDTO area : restrictedAreas) {
            if (position.getLatitude() >= area.getSoutheast().getLatitude()
                    && position.getLatitude() <= area.getNorthwest().getLatitude()
                    && position.getLongitude() >= area.getNorthwest().getLongitude()
                    && position.getLongitude() <= area.getSoutheast().getLongitude()) {
                return true;
            }
        }
        return false;
    }

    public List<Position> findAll() {
        return positionRepository.findAll();
    }

    public Position findById(int id) {
        return positionRepository
                .findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Position not found"));
    }

    public Position save(Position position) {
        return positionRepository.save(position);
    }

    public void deleteById(int id) {
        positionRepository.deleteById(id);
    }
}
