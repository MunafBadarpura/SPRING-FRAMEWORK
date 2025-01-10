package com.munaf.A12_PROD_READY_FEATURE.clients;

import com.munaf.A12_PROD_READY_FEATURE.dtos.EmployeeDTO;

import java.util.List;

public interface EmployeeClient {
    List<EmployeeDTO> getAllEmployee();

    EmployeeDTO getEmployeeById(Long id);

    EmployeeDTO createNewEmployee(EmployeeDTO employeeDTO);

    EmployeeDTO updateEmployeeById(Long id, EmployeeDTO employeeDTO);

    Boolean deleteEmployeeById(Long id);
}
