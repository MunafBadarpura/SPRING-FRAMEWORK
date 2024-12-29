package com.munaf.A11_DATA_MAPPING.services;

import com.munaf.A11_DATA_MAPPING.entities.DepartmentEntity;
import com.munaf.A11_DATA_MAPPING.entities.EmployeeEntity;
import com.munaf.A11_DATA_MAPPING.repositories.EmployeeRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeService {

    private EmployeeRepository employeeRepository;

    public EmployeeService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    public EmployeeEntity createNewEmployee(EmployeeEntity employeeEntity) {
        return employeeRepository.save(employeeEntity);
    }

    public EmployeeEntity getEmployeeById(Long employeeId) {
        return employeeRepository.findById(employeeId).orElse(null);
    }


    public DepartmentEntity getAssignedDepartmentOfManager(Long employeeId) {
        Optional<EmployeeEntity> employeeEntity = employeeRepository.findById(employeeId);
        return employeeEntity.map(employee -> employee.getManagedDepartment()).orElse(null);
    }
}
