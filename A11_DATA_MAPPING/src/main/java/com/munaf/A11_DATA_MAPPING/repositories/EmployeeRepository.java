package com.munaf.A11_DATA_MAPPING.repositories;

import com.munaf.A11_DATA_MAPPING.entities.EmployeeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepository extends JpaRepository<EmployeeEntity, Long> {

}
