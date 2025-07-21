package com.munaf.A26_JPA_NEW.repositories;

import com.munaf.A26_JPA_NEW.dto.BloodGroupCount;
import com.munaf.A26_JPA_NEW.dto.PatientDTO;
import com.munaf.A26_JPA_NEW.dto.PatientRecord;
import com.munaf.A26_JPA_NEW.entities.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface PatientRepo extends JpaRepository<Patient, Long> {


    @Query("SELECT p.name as name, p.address as address, p.bloodGroup as bloodGroup FROM Patient p WHERE p.id = :id")
    PatientRecord findPatientRecordById(Long id);


    @Query("SELECT new com.munaf.A26_JPA_NEW.dto.PatientDTO(p.name, p.address, p.bloodGroup) FROM Patient p WHERE p.id = :id")
    PatientDTO findPatientDTOById(Long id);


    @Query("SELECT new com.munaf.A26_JPA_NEW.dto.BloodGroupCount(p.bloodGroup, count(p)) FROM Patient p GROUP BY p.bloodGroup")
    List<BloodGroupCount> bloodGroupCount();


    @Transactional
    @Modifying
    @Query("UPDATE Patient p SET p.name = :name WHERE p.id = :id")
    int updatePatientNameById(String name, Long id); // return number of rows updated

}
