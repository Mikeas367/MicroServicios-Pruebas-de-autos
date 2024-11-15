package com.backend.positions.services;

import com.backend.positions.dtos.CoordinatesConfigurationDTO;
import com.backend.positions.dtos.CoordinatesDTO;
import com.backend.positions.dtos.RestrictedAreasDTO;
import com.backend.positions.dtos.TrialDTO;
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
    @Autowired
    private TrialDTOService trialService;

    public double round(double value) {
        return Math.round(value * 100.0) / 100.0;
    }

    public Boolean isInARestrictedArea(Position position, List<RestrictedAreasDTO> restrictedAreas) {
        for (RestrictedAreasDTO area : restrictedAreas) {
            // limites del area restringida
            double suresteLAT = round(area.getSureste().getLat());
            double suresteLON = round(area.getSureste().getLon());

            double noroesteLAT = round(area.getNoroeste().getLat());
            double noroesteLON = round(area.getNoroeste().getLon());

            // Coordenadas del vehículo
            double positionLAT = position.getLatitude();
            double positionLON = position.getLongitude();

            // Verificar si la posición del vehículo está dentro del rectángulo
            if (positionLAT >= suresteLAT && positionLAT <= noroesteLAT && // Latitud dentro del rango
                    positionLON >= noroesteLON && positionLON <= suresteLON) { // Longitud dentro del rango
                System.out.println("El auto " + position.getVehicleId() + " está en un área restringida.");

                Notification notification = new Notification();
                notification.setVehicleId(position.getVehicleId());
                notification.setMessage("El pajeraso esta en un area restringida.");
                notification.setTimestamp(LocalDateTime.now());
                notificationRepository.save(notification);

                return true;
            }
        }
        // Si ninguna área coincide, el vehículo no está en una zona restringida
        return false;
    }

    @Scheduled(fixedRate = 10000) // Cada 10 segundos
    public void updatePositions() {
        List<Position> positions = positionRepository.findAll();
        CoordinatesConfigurationDTO configuration = coordinatesService.getCoordinatesConfigurationDTO();
        //System.out.println("Datos Obtenidos del servicio Externo ---->" + configuration);
        List<TrialDTO> trialDTOS = trialService.getTrials();
        System.out.println("Pruebas Activas ----> " + trialDTOS);
        List<RestrictedAreasDTO> restrictedAreas = configuration.getZonasRestringidas();

        for (Position position : positions) {
            // Simular movimiento del vehículo
            // lo redondeo porque sino no llegan a coicidir por los  valores
            position.setLatitude(round(position.getLatitude() + 0.10));
            position.setLongitude(round(position.getLongitude() + 0.10));
            System.out.println("Posicion actual del vehiculo ->" + position.getLatitude() + " " + position.getLongitude());

            if (isInARestrictedArea(position, restrictedAreas)) {
                System.out.println("EL AUTO ESTA RE AFUERA, LO TENEMOS Q MATAR");
            }

            save(position);
        }

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
