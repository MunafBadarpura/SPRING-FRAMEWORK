package com.munaf.SpringBootWebDemo1.repositories;

import com.munaf.SpringBootWebDemo1.entities.EmployeeEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<EmployeeEntity, Long> {


}
