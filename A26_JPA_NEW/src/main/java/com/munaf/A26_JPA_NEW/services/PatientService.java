package com.munaf.A26_JPA_NEW.services;

import com.munaf.A26_JPA_NEW.entities.Patient;
import com.munaf.A26_JPA_NEW.enums.BloodGroup;
import com.munaf.A26_JPA_NEW.repositories.PatientRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class PatientService {

    private final PatientRepository patientRepository;


    public PatientService(PatientRepository patientRepository) {
        this.patientRepository = patientRepository;
    }



    public Patient createPatient() {
        Patient patient = new Patient();
        patient.setName("Munaf");
        patient.setAddress("Dhaka");
        patient.setBloodGroup(BloodGroup.O_POSITIVE);
        return patientRepository.save(patient);
    }

    @Transactional
    public Patient updatePatient(Long id) {
        Patient patient = patientRepository.findById(id).orElseThrow(() -> new RuntimeException("Patient not found"));
        patient.setName("DFSDFS NMAE");

        return patient;
    }



    public Patient getPatient(Long id) {
        return patientRepository.findById(id).orElseThrow(() -> new RuntimeException("Patient not found"));
    }

}
