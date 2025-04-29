package com.munaf.A16_SPRING_TESTING.controller;

import com.munaf.A16_SPRING_TESTING.TestContainerConfigurations;
import com.munaf.A16_SPRING_TESTING.dtos.EmployeeDTO;
import com.munaf.A16_SPRING_TESTING.entities.Employee;
import com.munaf.A16_SPRING_TESTING.enums.Department;
import com.munaf.A16_SPRING_TESTING.repositories.EmployeeRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.web.reactive.server.WebTestClient;

import java.util.List;


//@AutoConfigureWebTestClient(timeout = "100000") // this will autoconfigure webTestClient so we can autowire
//@Import(TestContainerConfigurations.class)
//@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
// ABOVE CODE MOVED TO AbstractIntegrationTest
class EmployeeControllerTestIT extends AbstractIntegrationTest{

    @Autowired
    private WebTestClient webTestClient;

    @Autowired
    private EmployeeRepository employeeRepository;

    private Employee testEmployee;

    @BeforeEach
    void setUp() {
        testEmployee = Employee.builder()
                .id(1L)
                .email("munaf@gmail.com")
                .name("Munaf")
                .age(20)
                .department(Department.IT)
                .build();
    }

    @AfterEach
    void tearDown() {
        employeeRepository.deleteAll();
    }

    @Test
    void testGetEmployeeById_success() {
        employeeRepository.save(testEmployee);

        webTestClient.get()
                .uri("/employee/{id}", testEmployee.getId())
                .exchange()
                .expectStatus().isOk()
                .expectBody(EmployeeDTO.class)
                .value(employeeDTO -> {
                    Assertions.assertThat(employeeDTO.getId()).isEqualTo(testEmployee.getId());
                    Assertions.assertThat(employeeDTO.getName()).isEqualTo(testEmployee.getName());
                    Assertions.assertThat(employeeDTO.getEmail()).isEqualTo(testEmployee.getEmail());
                    Assertions.assertThat(employeeDTO.getAge()).isEqualTo(testEmployee.getAge());
                    Assertions.assertThat(employeeDTO.getDepartment()).isEqualTo(testEmployee.getDepartment());
                });


//        webTestClient.get()
//                .uri("/employee/{id}", testEmployee.getId())
//                .exchange()
//                .expectStatus().isOk()
//                .expectBody()
//                .jsonPath("$.name").isEqualTo("Munaf")
//                .jsonPath("$.email").isEqualTo("munaf@gmail.com")
//                .jsonPath("$.age").isEqualTo(20)
//                .jsonPath("$.department").isEqualTo("IT");
    }

    @Test
    void testGetEmployeeById_failure() {
        webTestClient.get()
                .uri("employee/{id}", testEmployee.getId())
                .exchange()
                .expectStatus().isNotFound()
                .expectBody(String.class)
                .value(response -> Assertions.assertThat(response).isEqualTo("Employee not found"));
    }

    @Test
    void testCreateEmployee_whenEmployeeNotExists_thenReturnEmployeeDTO() {
        webTestClient.post()
                .uri("/employee")
                .bodyValue(EmployeeDTO.employeeToEmployeeDTO(testEmployee))
                .exchange()
                .expectStatus().isCreated()
                .expectBody(EmployeeDTO.class)
                .value(employeeDTO -> {
                    Assertions.assertThat(employeeDTO).isNotNull();
                    Assertions.assertThat(employeeDTO.getId()).isNotNull();
                    Assertions.assertThat(employeeDTO.getName()).isEqualTo(testEmployee.getName());
                    Assertions.assertThat(employeeDTO.getEmail()).isEqualTo(testEmployee.getEmail());
                    Assertions.assertThat(employeeDTO.getAge()).isEqualTo(testEmployee.getAge());
                    Assertions.assertThat(employeeDTO.getDepartment()).isEqualTo(testEmployee.getDepartment());
                });
    }

    @Test
    void testCreateEmployee_whenEmployeeAlreadyExists_thenThrowException() {
        employeeRepository.save(testEmployee);

        webTestClient.post()
                .uri("/employee")
                .bodyValue(EmployeeDTO.employeeToEmployeeDTO(testEmployee))
                .exchange()
                .expectStatus().isBadRequest()
                .expectBody(String.class)
                .value(response -> Assertions.assertThat(response).isEqualTo("Employee already exists"));
    }


    @Test
    void testGetAllEmployee() {
        Employee testEmployee2 = new Employee();
        testEmployee2.setId(2L);
        testEmployee2.setName("Munaf2");
        testEmployee2.setEmail("munaf2@gmail.com");
        testEmployee2.setAge(24);
        testEmployee2.setDepartment(Department.IT);
        employeeRepository.save(testEmployee);
        employeeRepository.save(testEmployee2);


        webTestClient.get()
                .uri("employee")
                .exchange()
                .expectStatus().isOk()
                .expectBody(List.class)
                .value(employeeList -> Assertions.assertThat(employeeList).hasSize(2));

    }

    @Test
    void testUpdateEmployee_whenEmployeeIdNotExists_thenThrowException() {
        webTestClient.put()
                .uri("employee/{id}", 0)
                .bodyValue(EmployeeDTO.employeeToEmployeeDTO(testEmployee))
                .exchange()
                .expectStatus().isNotFound()
                .expectBody(String.class)
                .value(response -> Assertions.assertThat(response).isEqualTo("Employee not found"));
    }

    @Test
    void testUpdateEmployee_whenEmployeeIdExists_thenUpdateEmployee() {
        employeeRepository.save(testEmployee);
        testEmployee.setName("updatedName");
        testEmployee.setDepartment(Department.HR);

        webTestClient.put()
                .uri("employee/{id}", testEmployee.getId())
                .bodyValue(EmployeeDTO.employeeToEmployeeDTO(testEmployee))
                .exchange()
                .expectStatus().isOk()
                .expectBody()
                .jsonPath("$.name").isEqualTo(testEmployee.getName())
                .jsonPath("$.department").isEqualTo(testEmployee.getDepartment().toString());

    }

    @Test
    void testDeleteEmployee_whenEmployeeIdNotExists_thenThrowException() {
        webTestClient.delete()
                .uri("employee/{id}", 0)
                .exchange()
                .expectStatus().isNotFound()
                .expectBody(String.class)
                .value(employeeDto -> Assertions.assertThat(employeeDto).isEqualTo("Employee not found"));
    }


    @Test
    void testDeleteEmployee_whenEmployeeIdExists_thenDeleteEmployee() {
        employeeRepository.save(testEmployee);

        webTestClient.delete()
                .uri("employee/{id}", testEmployee.getId())
                .exchange()
                .expectStatus().isOk()
                .expectBody(Void.class)
                .value(response -> Assertions.assertThat(response).isNull());

    }

}


/*
@AutoConfigureWebTestClient(timeout = "100000")
    : this will autoconfigure webTestClient so we can autowire
    : If the server doesn't respond within 100 seconds, the test will fail with a timeout error.

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
    : It starts the embedded server (like Tomcat or Jetty) on a random available port.

Common WebTestClient Response Methods:
    • exchange(): Executes the request and returns a WebTestClient.ResponseSpec
    • expectStatus(): Asserts the status code of the response.
    • expectBody(): Asserts the body of the response.
    • expectHeader(): Asserts the headers of the response.
    • .jsonPath("$.id").isNotEmpty()
    • .jsonPath("$.name").isEqualTo("Jane Doe")
    • .jsonPath("$.email").isEqualTo("jane.doe@example.com")

*/
























