package com.munaf.A09_SPRING_WEB_3.repositories;

import com.munaf.A09_SPRING_WEB_3.entities.DepartmentEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DepartmentRepository extends JpaRepository<DepartmentEntity, Long> {
}
