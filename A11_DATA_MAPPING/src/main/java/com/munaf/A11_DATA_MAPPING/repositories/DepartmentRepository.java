package com.munaf.A11_DATA_MAPPING.repositories;

import com.munaf.A11_DATA_MAPPING.entities.DepartmentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DepartmentRepository extends JpaRepository<DepartmentEntity, Long> {

}
