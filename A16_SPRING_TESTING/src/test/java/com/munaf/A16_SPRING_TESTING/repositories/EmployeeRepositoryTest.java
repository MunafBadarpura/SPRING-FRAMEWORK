package com.munaf.A16_SPRING_TESTING.repositories;

import com.munaf.A16_SPRING_TESTING.TestContainerConfigurations;
import com.munaf.A16_SPRING_TESTING.entities.Employee;
import com.munaf.A16_SPRING_TESTING.enums.Department;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

//@SpringBootTest
@Import(TestContainerConfigurations.class)
@DataJpaTest // Load only JPA-related beans and Automatically set H2 database
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE) // Don't replace the database
class EmployeeRepositoryTest {

    @Autowired
    private EmployeeRepository employeeRepository;

    private Employee employee;

    @BeforeEach
    void setUpEmployee() {
        employee = Employee.builder()
                .id(1L)
                .name("Munaf")
                .email("munaf@gmail.com")
                .age(20)
                .department(Department.IT)
                .build();
    }

    @Test
    void testExistsByEmail_whenEmailExists_thenReturnTrue() {
        // Arrange, Given
        employeeRepository.save(employee);

        // Act, When
        boolean isEmployeeExists = employeeRepository.existsByEmail("munaf@gmail.com");

        // Assert ,Then
        assertThat(isEmployeeExists)
                .isTrue()
                .isNotNull();
    }


    @Test
    void testExistsByEmail_whenEmailDoesNotExists_thenReturnFalse() {
        // Arrange, Given
        String notValidEmail = "notvalid@gmail.com";

        // Act, When
        boolean isEmployeeExists = employeeRepository.existsByEmail(notValidEmail);

        // Assert ,Then
        assertThat(isEmployeeExists)
                .isFalse()
                .isNotNull();
    }

}


/*

@SpringBootTest : load whole spring app and if we are using beans inside testing then it is necessary
@DataJpaTest : load only JPA related beans and not the whole app use for testing repositories
             : after test completion @DataJpaTest rollback to maintain db
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE) : Don't replace the database connection, Use the actual database that is configured
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.ANY) : Always replace the real database connection with an embedded/in-memory database
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.AUTO_CONFIGURED) : Only replace the database if it was autoconfigured by Spring Boot,
                                     Don't replace if the developer explicitly defined a datasource manually





**/