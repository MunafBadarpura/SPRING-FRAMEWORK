package com.munaf.A16_SPRING_TESTING.services;

import com.munaf.A16_SPRING_TESTING.dtos.EmployeeDTO;

import java.util.List;

public interface EmployeeService {

    EmployeeDTO createEmployee(EmployeeDTO employeeDTO);

    List<EmployeeDTO> getAllEmployee();

    EmployeeDTO getEmployeeById(Long id);

    EmployeeDTO updateEmployeeById(Long id, EmployeeDTO employeeDTO);

    void deleteEmployeeById(Long id);

}
