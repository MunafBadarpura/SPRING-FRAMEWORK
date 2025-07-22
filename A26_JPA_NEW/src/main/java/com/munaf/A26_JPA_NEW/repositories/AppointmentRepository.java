package com.munaf.A26_JPA_NEW.repositories;

import com.munaf.A26_JPA_NEW.entities.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AppointmentRepository extends JpaRepository<Appointment, Long> {
}