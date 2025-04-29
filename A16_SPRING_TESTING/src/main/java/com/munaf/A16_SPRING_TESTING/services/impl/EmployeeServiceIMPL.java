package com.munaf.A16_SPRING_TESTING.services.impl;

import com.munaf.A16_SPRING_TESTING.dtos.EmployeeDTO;
import com.munaf.A16_SPRING_TESTING.entities.Employee;
import com.munaf.A16_SPRING_TESTING.exceptions.InvalidInputException;
import com.munaf.A16_SPRING_TESTING.exceptions.ResourceNotFoundException;
import com.munaf.A16_SPRING_TESTING.repositories.EmployeeRepository;
import com.munaf.A16_SPRING_TESTING.services.EmployeeService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeServiceIMPL implements EmployeeService {

    private final EmployeeRepository employeeRepository;

    public EmployeeServiceIMPL(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @Override
    public EmployeeDTO createEmployee(EmployeeDTO employeeDTO) {
        if (employeeRepository.existsByEmail(employeeDTO.getEmail())) {
            throw new InvalidInputException("Employee already exists");
        }
        Employee savedEmployee = employeeRepository.save(employeeDTO.employeeDTOToEmployee());
        return EmployeeDTO.employeeToEmployeeDTO(savedEmployee);
    }

    @Override
    public List<EmployeeDTO> getAllEmployee() {
        List<Employee> employees = employeeRepository.findAll();
        return employees.stream()
                .map(employee -> EmployeeDTO.employeeToEmployeeDTO(employee))
                .toList();
    }

    @Override
    public EmployeeDTO getEmployeeById(Long id) {
        return EmployeeDTO.employeeToEmployeeDTO(employeeRepository.findById(id).
                orElseThrow(() -> new ResourceNotFoundException("Employee not found")));
    }

    @Override
    public EmployeeDTO updateEmployeeById(Long id, EmployeeDTO employeeDTO) {
        Employee employee = employeeRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Employee not found"));
        employee.setId(id);
        employee.setName(employeeDTO.getName());
        employee.setEmail(employeeDTO.getEmail());
        employee.setAge(employeeDTO.getAge());
        employee.setDepartment(employeeDTO.getDepartment());
        Employee updatedEmployee = employeeRepository.save(employee);

        return EmployeeDTO.employeeToEmployeeDTO(updatedEmployee);
    }

    @Override
    public void deleteEmployeeById(Long id) {
        if (employeeRepository.existsById(id)) {
            employeeRepository.deleteById(id);
            return;
        }
        throw new ResourceNotFoundException("Employee not found");
    }
}
