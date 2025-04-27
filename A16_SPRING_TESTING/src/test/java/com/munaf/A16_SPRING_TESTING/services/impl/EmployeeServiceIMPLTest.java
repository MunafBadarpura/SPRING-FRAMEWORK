package com.munaf.A16_SPRING_TESTING.services.impl;

import com.munaf.A16_SPRING_TESTING.TestContainerConfigurations;
import com.munaf.A16_SPRING_TESTING.dtos.EmployeeDTO;
import com.munaf.A16_SPRING_TESTING.entities.Employee;
import com.munaf.A16_SPRING_TESTING.enums.Department;
import com.munaf.A16_SPRING_TESTING.repositories.EmployeeRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.context.annotation.Import;

import java.util.Optional;

@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Import(TestContainerConfigurations.class)
@ExtendWith(MockitoExtension.class) // Tells that start using mocking
class EmployeeServiceIMPLTest {


    @Mock // put all mocks inside InjectMocks
    private EmployeeRepository employeeRepository;

    @InjectMocks // inject the mock
    private EmployeeServiceIMPL employeeService;

    @Test
    void testGetEmployeeById_whenEmployeeIdExists_thenReturnEmployeeDto() {
        // Arrange
        Long id = 1L;
        Employee mockedEmployee = Employee.builder()
                .id(id)
                .email("munaf@gmail.com")
                .name("Munaf")
                .age(20)
                .department(Department.IT)
                .build();
        Mockito.when(employeeRepository.findById(id)).thenReturn(Optional.of(mockedEmployee)); // stubbing

        // Act
        EmployeeDTO employeeDTO = employeeService.getEmployeeById(id);

        // Assert
        Assertions.assertThat(employeeDTO.getId()).isEqualTo(id);
        Assertions.assertThat(employeeDTO.getName()).isEqualTo("Munaf");
        Assertions.assertThat(employeeDTO.getDepartment()).isEqualTo(Department.IT);
    }

}


/*
@ExtendWith(MockitoExtension.class) : // Tells that start using mocking
@Mock : put all mocks inside InjectMocks
@InjectMocks : inject the mock


*/













