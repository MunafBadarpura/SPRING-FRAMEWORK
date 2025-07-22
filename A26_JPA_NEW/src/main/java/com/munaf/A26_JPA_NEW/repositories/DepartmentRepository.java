package com.munaf.A26_JPA_NEW.repositories;

import com.munaf.A26_JPA_NEW.entities.Department;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DepartmentRepository extends JpaRepository<Department, Long> {
}