package com.munaf.SpringBootWebDemo1.controllers;

import com.munaf.SpringBootWebDemo1.dto.EmployeeDto;
import com.munaf.SpringBootWebDemo1.entities.EmployeeEntity;
import com.munaf.SpringBootWebDemo1.repositories.EmployeeRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/employee") // parent path
public class EmployeeController {

    private final EmployeeRepository employeeRepository;

    public EmployeeController(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @GetMapping(path = "/getSecretMessage") // employee/getSecretMessage
    public String getSecretMessage(){
        return "Message : 768943";
    }

    @GetMapping(path = "/{empId}") // employee/{empId}
    public EmployeeEntity getEmployee(@PathVariable Long empId){
        return employeeRepository.findById(empId).orElse(null);
    }

    @GetMapping(path = "/getAllEmployee") // http://localhost:8080/employee/getEmp?age=15&name=Munaf
    public List<EmployeeEntity> getAllEmployee(@RequestParam(required = false) Integer age,
                                               @RequestParam(required = false) String name){
        return employeeRepository.findAll();
    }

    @GetMapping // employee/
    public String getHomePage(){
        return "HomePage";
    }

    @PostMapping // employee/
    public EmployeeEntity createEmployee(@RequestBody EmployeeEntity newEmployee){
        return employeeRepository.save(newEmployee);
    }
}
