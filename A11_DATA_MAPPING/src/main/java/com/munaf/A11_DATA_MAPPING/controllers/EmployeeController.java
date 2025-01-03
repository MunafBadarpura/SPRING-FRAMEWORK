package com.munaf.A11_DATA_MAPPING.controllers;

import com.munaf.A11_DATA_MAPPING.entities.DepartmentEntity;
import com.munaf.A11_DATA_MAPPING.entities.EmployeeEntity;
import com.munaf.A11_DATA_MAPPING.services.EmployeeService;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/employee")
public class EmployeeController {

    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }


    @PostMapping
    public EmployeeEntity createNewEmployee(@RequestBody EmployeeEntity employeeEntity){
        return employeeService.createNewEmployee(employeeEntity);
    }

    @GetMapping("{employeeId}")
    public EmployeeEntity getEmployeeById(@PathVariable Long employeeId){
        return employeeService.getEmployeeById(employeeId);
    }

//    @GetMapping("assignedDepartmentOfManager/{employeeId}")
//    public DepartmentEntity getAssignedDepartmentOfManager(@PathVariable Long employeeId){
//        return employeeService.getAssignedDepartmentOfManager(employeeId);
//    }

}
