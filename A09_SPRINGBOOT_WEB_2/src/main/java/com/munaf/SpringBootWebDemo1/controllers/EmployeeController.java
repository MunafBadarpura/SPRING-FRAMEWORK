package com.munaf.SpringBootWebDemo1.controllers;

import com.munaf.SpringBootWebDemo1.dto.EmployeeDto;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/employee") // parent path
public class EmployeeController {

    @GetMapping(path = "/getSecretMessage") // employee/getSecretMessage
    public String getSecretMessage(){
        return "Message : 768943";
    }

    @GetMapping(path = "/{empId}") // employee/{empId}
    public EmployeeDto getEmployee(@PathVariable Long empId){
        return new EmployeeDto(empId, "Munaf", 18, true);
    }

    @GetMapping(path = "/getEmp") // http://localhost:8080/employee/getEmp?age=15&name=Munaf
    public String getEmp(@RequestParam(required = false) Integer age,
                         @RequestParam(required = false) String name){
        return "Hi "+name+" "+ age;
    }

    @GetMapping // employee/
    public String getHomePage(){
        return "HomePage";
    }

    @PostMapping // employee/
    public EmployeeDto createEmployee(@RequestBody EmployeeDto newEmployee){
        newEmployee.setId(100L);
        return newEmployee;
    }
}
