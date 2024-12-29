package com.munaf.A11_DATA_MAPPING.services;

import com.munaf.A11_DATA_MAPPING.entities.DepartmentEntity;
import com.munaf.A11_DATA_MAPPING.entities.EmployeeEntity;
import com.munaf.A11_DATA_MAPPING.repositories.DepartmentRepository;
import com.munaf.A11_DATA_MAPPING.repositories.EmployeeRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
public class DepartmentService {

    private final DepartmentRepository departmentRepository;
    private final EmployeeRepository employeeRepository;

    public DepartmentService(DepartmentRepository departmentRepository, EmployeeRepository employeeRepository) {
        this.departmentRepository = departmentRepository;
        this.employeeRepository = employeeRepository;
    }

    public DepartmentEntity createNewDepartment(DepartmentEntity departmentEntity) {
        return departmentRepository.save(departmentEntity);
    }

    public DepartmentEntity getDepartmentById(Long id) {
        return departmentRepository.findById(id).orElse(null);
    }


    public DepartmentEntity assignManagerToDepartment(Long departmentId, Long employeeId) {
        Optional<DepartmentEntity> departmentEntity = departmentRepository.findById(departmentId);
        Optional<EmployeeEntity> employeeEntity = employeeRepository.findById(employeeId);

        if (departmentEntity.isPresent() && employeeEntity.isPresent()) {
                departmentEntity.get().setManager(employeeEntity.get());
                return departmentRepository.save(departmentEntity.get());
        }
        return null;

        // with lambda expression
//        return departmentEntity.flatMap(department ->
//            employeeEntity.map(employee -> {
//                department.setManager(employee);
//                return departmentRepository.save(department);
//            })).orElse(null);


    }
}
