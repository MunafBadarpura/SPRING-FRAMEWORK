package com.munaf.A26_JPA_NEW.repositories;

import com.munaf.A26_JPA_NEW.entities.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DoctorRepository extends JpaRepository<Doctor, Long> {
}