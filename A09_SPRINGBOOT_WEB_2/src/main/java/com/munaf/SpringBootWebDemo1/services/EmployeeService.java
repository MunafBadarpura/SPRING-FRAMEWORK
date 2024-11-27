package com.munaf.SpringBootWebDemo1.services;


import com.munaf.SpringBootWebDemo1.dto.EmployeeDto;
import com.munaf.SpringBootWebDemo1.entities.EmployeeEntity;
import com.munaf.SpringBootWebDemo1.repositories.EmployeeRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class EmployeeService {

    private final EmployeeRepository employeeRepository;
    private final ModelMapper modelMapper;


    public EmployeeService(EmployeeRepository employeeRepository, ModelMapper modelMapper) {
        this.employeeRepository = employeeRepository;
        this.modelMapper = modelMapper;
    }

    public EmployeeDto getEmployeeById(Long empId) {
        EmployeeEntity employeeEntity = employeeRepository.findById(empId).orElse(null);
        return modelMapper.map(employeeEntity, EmployeeDto.class);
    }

    public List<EmployeeDto> getAllEmployee() {
        List<EmployeeEntity> employeeEntities =  employeeRepository.findAll();
        return employeeEntities
                .stream()
                .map(employeeEntity -> modelMapper.map(employeeEntity, EmployeeDto.class))
                .collect(Collectors.toList());

    }

    public EmployeeDto createEmployee(EmployeeDto newEmployee) {
        EmployeeEntity toSaveEntity = modelMapper.map(newEmployee, EmployeeEntity.class);
        EmployeeEntity savedEmployeeEntity = employeeRepository.save(toSaveEntity);
        return modelMapper.map(savedEmployeeEntity, EmployeeDto.class);
    }
}
