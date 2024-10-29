package com.backend.trials.repositories;

import com.backend.trials.models.Trial;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TrialRepository extends JpaRepository<Trial, Integer> {
}
