package com.munaf.SpringBootWebDemo1.controllers;

import com.munaf.SpringBootWebDemo1.dto.EmployeeDto;
import com.munaf.SpringBootWebDemo1.entities.EmployeeEntity;
import com.munaf.SpringBootWebDemo1.services.EmployeeService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/employee") // parent path
public class EmployeeController {

    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping(path = "/getSecretMessage") // employee/getSecretMessage
    public String getSecretMessage(){
        return "Message : 768943";
    }

    @GetMapping(path = "/{empId}") // employee/{empId}
    public EmployeeDto getEmployee(@PathVariable Long empId){
        return employeeService.getEmployeeById(empId);
    }

    @GetMapping(path = "/getAllEmployee") // http://localhost:8080/employee/getEmp?age=15&name=Munaf
    public List<EmployeeDto> getAllEmployee(@RequestParam(required = false) Integer age,
                                               @RequestParam(required = false) String name){
        return employeeService.getAllEmployee();
    }

    @GetMapping // employee/
    public String getHomePage(){
        return "HomePage";
    }

    @PostMapping // employee/
    public EmployeeDto createEmployee(@RequestBody EmployeeDto newEmployee){
        return employeeService.createEmployee(newEmployee);
    }
}
