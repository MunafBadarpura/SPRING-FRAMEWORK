package com.munaf.A09_SPRING_WEB_3.controllers;

import com.munaf.A09_SPRING_WEB_3.configs.HealthCheck;
import com.munaf.A09_SPRING_WEB_3.dto.DepartmentDTO;
import com.munaf.A09_SPRING_WEB_3.exceptions.ResourceNotFoundException;
import com.munaf.A09_SPRING_WEB_3.services.DepartmentService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("department")
public class DepartmentController {

    private final DepartmentService departmentService;

    public DepartmentController(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }

    @GetMapping("/health-check")
    public ResponseEntity<HealthCheck> healthCheck(){
        return new ResponseEntity<>(new HealthCheck(), HttpStatus.OK);
    }

    @GetMapping()
    public ResponseEntity<List<DepartmentDTO>> getAllDepartment(){
        List<DepartmentDTO> departmentDTOList = departmentService.getAllDepartment();
        return ResponseEntity.ok(departmentDTOList);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DepartmentDTO> getDepartmentById(@PathVariable Long id) {
        Optional<DepartmentDTO> optionalDepartmentDTO = departmentService.getDepartmentById(id);

        return optionalDepartmentDTO
                .map(departmentDTO -> ResponseEntity.ok(departmentDTO))
                .orElseThrow(() -> new ResourceNotFoundException("Department Not available with id : " +id));

    }

    @PostMapping
    public ResponseEntity<DepartmentDTO> createNewDepartment(@RequestBody @Valid DepartmentDTO departmentDTO) {
        DepartmentDTO savedDepartmentDTO = departmentService.createNewDepartment(departmentDTO);
        return new ResponseEntity<>(savedDepartmentDTO, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<DepartmentDTO> updateDepartment(@PathVariable Long id, @RequestBody @Valid DepartmentDTO departmentDTO) {
        DepartmentDTO savedDepartmentDTO = departmentService.updateDepartment(id, departmentDTO);
        return new ResponseEntity<>(savedDepartmentDTO, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> deleteDepartment(@PathVariable Long id){
        departmentService.deleteDepartment(id);
        return new ResponseEntity<>(true, HttpStatus.OK);
    }

}
