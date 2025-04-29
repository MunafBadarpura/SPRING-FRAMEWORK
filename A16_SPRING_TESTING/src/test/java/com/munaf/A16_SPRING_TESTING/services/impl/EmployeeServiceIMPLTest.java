package com.munaf.A16_SPRING_TESTING.services.impl;

import com.munaf.A16_SPRING_TESTING.TestContainerConfigurations;
import com.munaf.A16_SPRING_TESTING.dtos.EmployeeDTO;
import com.munaf.A16_SPRING_TESTING.entities.Employee;
import com.munaf.A16_SPRING_TESTING.enums.Department;
import com.munaf.A16_SPRING_TESTING.exceptions.InvalidInputException;
import com.munaf.A16_SPRING_TESTING.exceptions.ResourceNotFoundException;
import com.munaf.A16_SPRING_TESTING.repositories.EmployeeRepository;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.context.annotation.Import;

import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.*;

@Slf4j
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Import(TestContainerConfigurations.class)
@ExtendWith(MockitoExtension.class) // Tells that start using mocking
class EmployeeServiceIMPLTest {


    @Mock // put all mocks inside InjectMocks
    private EmployeeRepository employeeRepository;

    @InjectMocks // inject all mocks here
    private EmployeeServiceIMPL employeeService;

    private Employee mockedEmployee;

    @BeforeEach
    void setUp() {
        mockedEmployee = Employee.builder()
                .id(1L)
                .email("munaf@gmail.com")
                .name("Munaf")
                .age(20)
                .department(Department.IT)
                .build();
    }

    // getEmployeeById
    @Test
    void testGetEmployeeById_whenEmployeeIdExists_thenReturnEmployeeDto() {
        // Arrange
        Long id = mockedEmployee.getId();
        Mockito.when(employeeRepository.findById(id)).thenReturn(Optional.of(mockedEmployee)); // stubbing

        // Act
        EmployeeDTO employeeDTO = employeeService.getEmployeeById(id);

        // Assert
        Assertions.assertThat(employeeDTO.getId()).isEqualTo(id);
        Assertions.assertThat(employeeDTO.getName()).isEqualTo("Munaf");
        Assertions.assertThat(employeeDTO.getDepartment()).isEqualTo(Department.IT);

        Mockito.verify(employeeRepository).findById(id); // check that this method is called or not
         // Mockito.verify(employeeRepository).save(null); // test fail because save method not called

        Mockito.verify(employeeRepository, Mockito.times(1)).findById(id);
        Mockito.verify(employeeRepository, Mockito.only()).findById(id);
        Mockito.verify(employeeRepository, Mockito.atLeast(1)).findById(id);
        Mockito.verify(employeeRepository, Mockito.atMost(10)).findById(id);

    }

    @Test
    void testGetEmployeeById_whenEmployeeIdNotExists_thenThrowResourceNotFoundException() {
        // Arrange
        Mockito.when(employeeRepository.findById(anyLong())).thenReturn(Optional.empty());

        // Act and Assert
        Assertions.assertThatThrownBy(() -> employeeService.getEmployeeById(1L))
                .isInstanceOf(ResourceNotFoundException.class)
                .hasMessage("Employee not found");

        Mockito.verify(employeeRepository, Mockito.atLeastOnce()).findById(1L);
    }


    // createEmployee
    @Test
    void testCreateEmployee_whenEmailAlreadyNotExists_thenReturnEmployeeDto() {
        // Arrange
        Mockito.when(employeeRepository.existsByEmail(mockedEmployee.getEmail())).thenReturn(false);
        Mockito.when(employeeRepository.save(mockedEmployee)).thenReturn(mockedEmployee);

        // Act
        EmployeeDTO savedEmployeeDTO = employeeService.createEmployee(EmployeeDTO.employeeToEmployeeDTO(mockedEmployee));

        // Assert
        Assertions.assertThat(savedEmployeeDTO)
                .isNotNull()
                .isInstanceOf(EmployeeDTO.class);

        ArgumentCaptor<Employee> employeeArgumentCaptor = ArgumentCaptor.forClass(Employee.class);
        Mockito.verify(employeeRepository).save(employeeArgumentCaptor.capture());
        Assertions.assertThat(employeeArgumentCaptor.getValue())
                        .isNotNull()
                        .isEqualTo(mockedEmployee);

        System.out.println(employeeArgumentCaptor.getValue());

    }

    @Test
    void testCreateEmployee_whenEmailAlreadyExists_thenReturnInvalidInputException() {
        // Arrange
        Mockito.when(employeeRepository.existsByEmail(anyString())).thenReturn(true);

        // Act, Assert
        Assertions.assertThatThrownBy(() -> employeeService.createEmployee(EmployeeDTO.employeeToEmployeeDTO(mockedEmployee)))
                .isInstanceOf(InvalidInputException.class)
                .hasMessage("Employee already exists");

        Mockito.verify(employeeRepository, Mockito.atLeastOnce()).existsByEmail(anyString());
        Mockito.verify(employeeRepository, Mockito.never()).save(any());
    }

    // getAllEmployee
    @Test
    void testGetAllEmployee_whenEmployeesExists_thenReturnEmployeeDTOList() {
        // Arrange
        Employee mockedEmployee2  = mockedEmployee;
        mockedEmployee2.setId(2L);

        List<Employee> employeeList = List.of(mockedEmployee, mockedEmployee2);
        Mockito.when(employeeRepository.findAll()).thenReturn(employeeList);

        // Act
        List<EmployeeDTO> savedEmployeeDTOList = employeeService.getAllEmployee();

        // Assert
        Assertions.assertThat(savedEmployeeDTOList)
                .isNotNull()
                .isNotEmpty()
                .hasSize(2)
                .isInstanceOf(List.class);
    }

    // deleteEmployeeById
    @Test
    void testDeleteEmployeeById_whenEmployeeIdExists_thenDeleteEmployee() {
        // Arrange
        Mockito.when(employeeRepository.existsById(anyLong())).thenReturn(true);

        // Act
        Assertions.assertThatCode(() -> employeeService.deleteEmployeeById(anyLong()))
                .doesNotThrowAnyException();


        // Assert
        Mockito.verify(employeeRepository).existsById(anyLong());
        Mockito.verify(employeeRepository).deleteById(anyLong());

    }

    @Test
    void testDeleteEmployeeById_whenEmployeeIdNotExists_thenThrowResourceNotFoundException() {
        // Arrange
        Mockito.when(employeeRepository.existsById(anyLong())).thenReturn(false);

        // Act and Assert
        Assertions.assertThatThrownBy(() -> employeeService.deleteEmployeeById(anyLong()))
                .isInstanceOf(ResourceNotFoundException.class)
                .hasMessage("Employee not found");

        Mockito.verify(employeeRepository).existsById(anyLong());
        Mockito.verify(employeeRepository, Mockito.never()).deleteById(anyLong());
    }


    // updateEmployeeById
    @Test
    void testUpdateEmployeeById_whenEmployeeExists_thenReturnUpdatedEmployeeDTO() {
        // Arrange
        Mockito.when(employeeRepository.findById(anyLong())).thenReturn(Optional.of(mockedEmployee));
        Mockito.when(employeeRepository.save(any())).thenReturn(mockedEmployee);

        // Act
        EmployeeDTO updatedEmployeeDTO = employeeService.updateEmployeeById(anyLong(), EmployeeDTO.employeeToEmployeeDTO(mockedEmployee));

        // Assert
        Assertions.assertThat(updatedEmployeeDTO)
                .isNotNull()
                .isInstanceOf(EmployeeDTO.class);
        Assertions.assertThat(updatedEmployeeDTO.getEmail()).isEqualTo(mockedEmployee.getEmail());


        Mockito.verify(employeeRepository, Mockito.atLeastOnce()).save(any());
        Mockito.verify(employeeRepository, Mockito.atLeastOnce()).findById(anyLong());
    }

    @Test
    void testUpdateEmployeeById_whenEmployeeNotExists_thenThrowResourceNotFoundException() {
        // Arrange
        Mockito.when(employeeRepository.findById(anyLong())).thenReturn(Optional.empty());

        // Assert And Act
        Assertions.assertThatThrownBy(() -> employeeService.updateEmployeeById(anyLong(), EmployeeDTO.employeeToEmployeeDTO(mockedEmployee)))
                .isInstanceOf(ResourceNotFoundException.class)
                .hasMessage("Employee not found");

        Mockito.verify(employeeRepository,Mockito.atLeastOnce()).findById(anyLong());
        Mockito.verify(employeeRepository,Mockito.never()).save(any());
    }
}


/*
@ExtendWith(MockitoExtension.class) : // Tells that start using mocking
@Mock : put all mocks inside InjectMocks
@Spy : use original bean and not create a fake bean for testing
@InjectMocks : inject the mock

3. Verifying Methods
    Mockito.verify(T mock): Verifies that a method was called on a mock.
    Mockito.verify(T mock, VerificationMode mode): Verifies that a method was called with a specific verification mode (e.g., times, never).

    =>VerificationMode modes:
        • times(int wantedNumberOfInvocations): Verifies the exact number of invocations.
        • never(): Verifies that a method was never called.
        • atLeastOnce(): Verifies that a method was called at least once.
        • atLeast(int minNumberOfInvocations): at least called x times
        • atMost(int maxNumberOfInvocations): at most called x times
        • only(): Verifies that no other method was called on the mock.
*/













