package com.munaf.A11_DATA_MAPPING.services;

import com.munaf.A11_DATA_MAPPING.entities.DepartmentEntity;
import com.munaf.A11_DATA_MAPPING.entities.EmployeeEntity;
import com.munaf.A11_DATA_MAPPING.repositories.DepartmentRepository;
import com.munaf.A11_DATA_MAPPING.repositories.EmployeeRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
public class DepartmentService {

    private final DepartmentRepository departmentRepository;
    private final EmployeeRepository employeeRepository;

    public DepartmentService(DepartmentRepository departmentRepository, EmployeeRepository employeeRepository) {
        this.departmentRepository = departmentRepository;
        this.employeeRepository = employeeRepository;
    }

    public DepartmentEntity createNewDepartment(DepartmentEntity departmentEntity) {
        return departmentRepository.save(departmentEntity);
    }

    public DepartmentEntity getDepartmentById(Long id) {
        return departmentRepository.findById(id).orElse(null);
    }


    public DepartmentEntity assignManagerToDepartment(Long departmentId, Long employeeId) {
        Optional<DepartmentEntity> OptionalDepartmentEntity = departmentRepository.findById(departmentId);
        Optional<EmployeeEntity> OptionalEmployeeEntity = employeeRepository.findById(employeeId);

        if (OptionalDepartmentEntity.isPresent() && OptionalEmployeeEntity.isPresent()) {
            DepartmentEntity departmentEntity = OptionalDepartmentEntity.get();
            EmployeeEntity employeeEntity = OptionalEmployeeEntity.get();

            departmentEntity.setManager(employeeEntity);
            return departmentRepository.save(departmentEntity);
        }
        else return null;

        // with lambda expression
//        return OptionalDepartmentEntity.flatMap(department ->
//            OptionalEmployeeEntity.map(employee -> {
//                department.setManager(employee);
//                return departmentRepository.save(department);
//            })).orElse(null);

    }

    public DepartmentEntity getAssignedDepartmentOfManager(Long employeeId) {
        Optional<EmployeeEntity> employeeEntity = employeeRepository.findById(employeeId);
        return employeeEntity.map(employee -> employee.getManagedDepartment()).orElse(null);
    }

    // Second Part Code

    public DepartmentEntity assignWorkerToDepartment(Long departmentId, Long employeeId) {
        Optional<DepartmentEntity> OptionalDepartmentEntity = departmentRepository.findById(departmentId);
        Optional<EmployeeEntity> OptionalEmployeeEntity = employeeRepository.findById(employeeId);

        if (OptionalDepartmentEntity.isPresent() && OptionalEmployeeEntity.isPresent()) {
            DepartmentEntity departmentEntity = OptionalDepartmentEntity.get();
            EmployeeEntity employeeEntity = OptionalEmployeeEntity.get();

            employeeEntity.setWorkDepartment(departmentEntity); // saving department in employee
            employeeRepository.save(employeeEntity);

            departmentEntity.getWorkers().add(employeeEntity); // saving employee in department worker list
            return departmentEntity;
        }
        else return null;
    }

    public DepartmentEntity assignFreelancersToDepartment(Long departmentId, Long employeeId) {
        Optional<DepartmentEntity> OptionalDepartmentEntity = departmentRepository.findById(departmentId);
        Optional<EmployeeEntity> OptionalEmployeeEntity = employeeRepository.findById(employeeId);

        if (OptionalDepartmentEntity.isPresent() && OptionalEmployeeEntity.isPresent()) {
            DepartmentEntity departmentEntity = OptionalDepartmentEntity.get();
            EmployeeEntity employeeEntity = OptionalEmployeeEntity.get();

            employeeEntity.getFreelancerDepartment().add(departmentEntity);
            employeeRepository.save(employeeEntity);

            departmentEntity.getFreelancers().add(employeeEntity);
            return departmentEntity;

        }
        else return null;
    }
}
