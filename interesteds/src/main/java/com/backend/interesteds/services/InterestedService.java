package com.backend.interesteds.services;

import com.backend.interesteds.exceptions.InvalidDataException;
import com.backend.interesteds.exceptions.ResourceNotFoundExeption;
import com.backend.interesteds.models.Interested;
import com.backend.interesteds.repositories.InterestedRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class InterestedService {
    @Autowired
    private InterestedRepository interestedRepository;

    public List<Interested> findAll() {
        return interestedRepository.findAll();
    }

    public Interested save(Interested interested) {
        return interestedRepository.save(interested);
    }

    public Interested getById(int id) {
        return interestedRepository
                .findById(id)
                .orElseThrow(()-> new ResourceNotFoundExeption("iterested not found"));
    }

    public void delete(int id) {
        interestedRepository.deleteById(id);
    }
    // no puede haber 2 personas con el mismo documento
    public boolean existsByDocument(String documentType, String documentNumber){
        return interestedRepository.existsByDocumentTypeAndDocumentNumber(documentType, documentNumber);
    }

    public boolean existByDriverLicenseNumber(String driverLicenseNumber){
        return interestedRepository.existsByDriverLicenseNumber(driverLicenseNumber);
    }

    // ---- Metodos de validacion ----

    public void validateRequiredFields(Interested i){

        if (i.getDocumentNumber() == null || i.getDocumentNumber().isEmpty()) {
            throw new InvalidDataException("Document number is required");
        }

        if (i.getDocumentType() == null || i.getDocumentType().isEmpty()) {
            throw new InvalidDataException("Document type is required");
        }

        if (i.getDriverLicenseNumber() == null || i.getDriverLicenseNumber().isEmpty()) {
            throw new InvalidDataException("Driver license number is required");
        }

        if (i.getFirstName() == null || i.getFirstName().isEmpty()) {
            throw new InvalidDataException("First name is required");
        }

        if (i.getLastName() == null || i.getLastName().isEmpty()) {
            throw new InvalidDataException("Last name is required");
        }

        if (i.getLicenseExpirationDate() == null) {
            throw new InvalidDataException("The license expiration Date is required");
        }
    }

    public void validateLicense(Interested i){

        if(existByDriverLicenseNumber(i.getDriverLicenseNumber())){
            throw new InvalidDataException("the driver license number already exists");
        }

        // supongo que no se puede crear un interesado con la licencia expirada (No lo deja en claro en el enunciado)
        if (i.getLicenseExpirationDate().isBefore(LocalDateTime.now())) {
            throw new InvalidDataException("the license is expired");
        }

        if (i.isRestricted()){
            throw new InvalidDataException("the interested is restricted");
        }
    }

    public void validateDocument(Interested i){
        if (existsByDocument(i.getDocumentType(), i.getDocumentNumber())) {
            throw new InvalidDataException("the document number already exists");
        }
    }

}
