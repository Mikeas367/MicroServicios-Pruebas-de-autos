package com.backend.vehicles.repositories;

import com.backend.vehicles.models.Model;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ModelRepository extends JpaRepository<Model, Integer> {
    boolean existsModelByModelName(String modelName);
}
