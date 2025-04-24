package com.munaf.A16_SPRING_TESTING.dtos;

import com.munaf.A16_SPRING_TESTING.entities.Employee;
import com.munaf.A16_SPRING_TESTING.enums.Department;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeDTO {

    private Long id;

    private String name;

    private String email;

    private Integer age;

    private Department department;

    public static EmployeeDTO employeeToEmployeeDTO(Employee employee) {
        return new EmployeeDTO(
                employee.getId(),
                employee.getName(),
                employee.getEmail(),
                employee.getAge(),
                employee.getDepartment()
        );
    }

    public Employee employeeDTOToEmployee() {
        return new Employee(
                this.getId(),
                this.getName(),
                this.getEmail(),
                this.getAge(),
                this.getDepartment()
        );
    }

}
