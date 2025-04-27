package com.munaf.A16_SPRING_TESTING.repositories;

import com.munaf.A16_SPRING_TESTING.entities.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    boolean existsByEmail(String email);
}
