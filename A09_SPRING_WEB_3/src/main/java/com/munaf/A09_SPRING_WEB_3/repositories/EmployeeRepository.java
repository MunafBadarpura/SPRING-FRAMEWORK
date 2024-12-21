package com.munaf.A09_SPRING_WEB_3.repositories;

import com.munaf.A09_SPRING_WEB_3.entities.EmployeeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeeRepository extends JpaRepository<EmployeeEntity, Long> {

    List<EmployeeEntity> findByName(String name);

}
