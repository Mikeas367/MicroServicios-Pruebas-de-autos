package com.backend.trials.repositories;

import com.backend.trials.models.Trial;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface TrialRepository extends JpaRepository<Trial, Integer> {
    boolean existsByVehicleIdAndTrialEndDateIsNull(int vehicleId);

    List<Trial> findByTrialStartDateAfterAndTrialEndDateIsNull(LocalDateTime date);
}