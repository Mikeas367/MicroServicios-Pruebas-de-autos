package com.backend.interesteds.repositories;

import com.backend.interesteds.models.Interested;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InterestedRepository extends JpaRepository<Interested, Integer> {
    boolean existsByDocumentTypeAndDocumentNumber(String documentType, String documentNumber);

    boolean existsByDriverLicenseNumber(String driverLicenseNumber);
}
