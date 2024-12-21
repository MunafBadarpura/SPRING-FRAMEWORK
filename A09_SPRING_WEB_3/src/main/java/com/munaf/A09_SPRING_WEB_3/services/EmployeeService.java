package com.munaf.A09_SPRING_WEB_3.services;

import com.munaf.A09_SPRING_WEB_3.dto.EmployeeDTO;
import com.munaf.A09_SPRING_WEB_3.entities.EmployeeEntity;
import com.munaf.A09_SPRING_WEB_3.exceptions.ResourceNotFoundException;
import com.munaf.A09_SPRING_WEB_3.repositories.EmployeeRepository;
import org.modelmapper.ModelMapper;
import org.springframework.data.util.ReflectionUtils;
import org.springframework.stereotype.Service;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class EmployeeService {

    private final EmployeeRepository employeeRepository;
    private final ModelMapper mapper;

    public EmployeeService(EmployeeRepository employeeRepository, ModelMapper mapper) {
        this.employeeRepository = employeeRepository;
        this.mapper = mapper;
    }


    public Optional<EmployeeDTO> getEmployeeById(Long id) {
        Optional<EmployeeEntity> optionalEmployeeEntity = employeeRepository.findById(id);
        return optionalEmployeeEntity.map(employeeEntity -> mapper.map(employeeEntity, EmployeeDTO.class));
    }

    public List<EmployeeDTO> getAllEmployees() {
        List<EmployeeEntity> employeeEntities=  employeeRepository.findAll(); //storing entities list
        return employeeEntities
                .stream()
                .map(n -> mapper.map(n, EmployeeDTO.class))
                .collect(Collectors.toList());
    }

    public EmployeeDTO createNewEmployee(EmployeeDTO newEmployee) {
        EmployeeEntity newEmployeeEntity = mapper.map(newEmployee,EmployeeEntity.class);
        EmployeeEntity employeeEntity = employeeRepository.save(newEmployeeEntity);
        return mapper.map(employeeEntity, EmployeeDTO.class);
    }

    public void isEmployeeExist(Long id){
        boolean exist = employeeRepository.existsById(id);
        if (!exist) throw new ResourceNotFoundException("Employee Not Exist With ID : " + id);
    }

    public EmployeeDTO updateEmployeeById(Long id,EmployeeDTO newEmployee) {
        isEmployeeExist(id);
        EmployeeEntity newEmployeeEntity = mapper.map(newEmployee, EmployeeEntity.class);
        newEmployeeEntity.setId(id);
        EmployeeEntity savedEmployeeEntity = employeeRepository.save(newEmployeeEntity);
        return mapper.map(savedEmployeeEntity, EmployeeDTO.class);
    }

    public Boolean deleteEmployeeById(Long id) {
        isEmployeeExist(id);
        employeeRepository.deleteById(id);
        return true;
    }


    public EmployeeDTO partialEmployeeById(Long id, Map<String, Object> updates) {
        isEmployeeExist(id);
        EmployeeEntity employeeEntity = employeeRepository.findById(id).get();

        updates.forEach((field, value) -> {
            Field fieldToBeUpdated = ReflectionUtils.findRequiredField(EmployeeEntity.class,field); // find field from EmployeeEntity
            fieldToBeUpdated.setAccessible(true); // for that field access give true cuz of private
            ReflectionUtils.setField(fieldToBeUpdated, employeeEntity, value); // field, class, value
        });

        EmployeeEntity savedEmployeeEntity = employeeRepository.save(employeeEntity);
        return mapper.map(savedEmployeeEntity, EmployeeDTO.class);

    }


}
