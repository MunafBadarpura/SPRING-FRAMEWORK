package com.munaf.A09_SPRING_WEB_3.controllers;

import com.munaf.A09_SPRING_WEB_3.configs.HealthCheck;
import com.munaf.A09_SPRING_WEB_3.dto.EmployeeDTO;
import com.munaf.A09_SPRING_WEB_3.exceptions.ResourceNotFoundException;
import com.munaf.A09_SPRING_WEB_3.services.EmployeeService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RequestMapping("employee")
@RestController
public class EmployeeController {


    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }


    @GetMapping("/health-check")
    public ResponseEntity<HealthCheck> healthCheck(){
        return new ResponseEntity<>(new HealthCheck(), HttpStatus.OK);
    }

    @GetMapping("getEmployeeById/{empId}")
    public ResponseEntity<EmployeeDTO> getEmployeeById(@PathVariable(name = "empId") Long id){
        Optional<EmployeeDTO> optionalEmployeeDTO = employeeService.getEmployeeById(id);

//        if (optionalEmployeeDTO.isPresent()){
//            return new ResponseEntity<>(optionalEmployeeDTO.get(), HttpStatus.OK);
//        }else {
//            throw new ResourceNotFoundException("Employee Not Found");
//        }

        // With Lambda

        return optionalEmployeeDTO
                .map(employeeDTO -> ResponseEntity.ok(employeeDTO))
                .orElseThrow(() -> new ResourceNotFoundException("Employee Not Found with ID : "+id) );
    }

    @GetMapping("getAllEmployees")
    public ResponseEntity<List<EmployeeDTO>> getAllEmployees(){
//        return new ResponseEntity<>(employeeService.getAllEmployees(), HttpStatus.OK);
          return ResponseEntity.ok(employeeService.getAllEmployees());
    }

    @PostMapping("/createNewEmployee")
    public ResponseEntity<EmployeeDTO> createNewEmployee(@RequestBody @Valid EmployeeDTO newEmployee){
        EmployeeDTO employeeDTO = employeeService.createNewEmployee(newEmployee);
        return new ResponseEntity<>(employeeDTO, HttpStatus.CREATED);
    }

    @PutMapping("/updateEmployeeById/{empId}")
    public ResponseEntity<EmployeeDTO> updateEmployeeById(@PathVariable(name = "empId") Long id,@RequestBody @Valid EmployeeDTO newEmployee){
//        return new ResponseEntity<>(employeeService.updateEmployeeById(id,newEmployee), HttpStatus.OK);
        return ResponseEntity.ok(employeeService.updateEmployeeById(id,newEmployee));
    }

    @DeleteMapping("deleteEmployeeById/{empId}")
    public ResponseEntity<String> deleteEmployeeById(@PathVariable(name = "empId") Long id) {
//        Boolean gotDeleted = employeeService.deleteEmployeeById(id);

//        if(gotDeleted) return new ResponseEntity<>(true, HttpStatus.OK);
//        else return new ResponseEntity<>(HttpStatus.NOT_FOUND);

//        if(gotDeleted) return ResponseEntity.ok(true);
//        else return ResponseEntity.notFound().build();
         employeeService.deleteEmployeeById(id);
         return new ResponseEntity<>("DELETED", HttpStatus.OK);
    }

    @PatchMapping("partialEmployeeById/{empId}")
    public ResponseEntity<EmployeeDTO> partialEmployeeById(@PathVariable(name = "empId") Long id, @RequestBody Map<String,Object> updates) {
        EmployeeDTO employeeDTO = employeeService.partialEmployeeById(id, updates);
        if(employeeDTO == null) return ResponseEntity.notFound().build();
        else return ResponseEntity.ok(employeeDTO);
    }

}
