package com.munaf.A09_SPRING_WEB_3.services;

import com.munaf.A09_SPRING_WEB_3.dto.DepartmentDTO;
import com.munaf.A09_SPRING_WEB_3.entities.DepartmentEntity;
import com.munaf.A09_SPRING_WEB_3.exceptions.ResourceNotFoundException;
import com.munaf.A09_SPRING_WEB_3.repositories.DepartmentRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class DepartmentService {

    private final DepartmentRepository departmentRepository;
    private final ModelMapper mapper;

    public DepartmentService(DepartmentRepository departmentRepository, ModelMapper mapper) {
        this.departmentRepository = departmentRepository;
        this.mapper = mapper;
    }


    public List<DepartmentDTO> getAllDepartment() {
        List<DepartmentEntity> departmentEntityList = departmentRepository.findAll();

        return departmentEntityList
                .stream()
                .map(n -> mapper.map(n, DepartmentDTO.class))
                .collect(Collectors.toList());
    }

    public Optional<DepartmentDTO> getDepartmentById(Long id) {
        Optional<DepartmentEntity> optionalDepartmentEntity = departmentRepository.findById(id);
        Optional<DepartmentDTO> optionalDepartmentDTO = optionalDepartmentEntity.map(departmentEntity -> mapper.map(departmentEntity, DepartmentDTO.class));
        return optionalDepartmentDTO;
    }


    public DepartmentDTO createNewDepartment(DepartmentDTO departmentDTO) {
        DepartmentEntity departmentEntity = mapper.map(departmentDTO, DepartmentEntity.class);
        return mapper.map(departmentRepository.save(departmentEntity), DepartmentDTO.class);
    }

    public void departmentExist(Long id){
        boolean exist = departmentRepository.existsById(id);
        if (!exist) throw new ResourceNotFoundException("Department not exist with id " +id);
    }

    public DepartmentDTO updateDepartment(Long id, DepartmentDTO departmentDTO) {
        departmentExist(id);
        DepartmentEntity departmentEntity = mapper.map(departmentDTO, DepartmentEntity.class);
        departmentEntity.setId(id);
        return mapper.map(departmentRepository.save(departmentEntity), DepartmentDTO.class);
    }

    public void deleteDepartment(Long id) {
        departmentExist(id);
        departmentRepository.deleteById(id);
    }
}
