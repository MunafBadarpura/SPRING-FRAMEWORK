package com.munaf.SpringBootWebDemo1.repositories;

import com.munaf.SpringBootWebDemo1.entities.EmployeeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepository extends JpaRepository<EmployeeEntity, Long> {

}
