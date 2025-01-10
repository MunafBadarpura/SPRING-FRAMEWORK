package com.munaf.A12_PROD_READY_FEATURE.dtos;

import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class EmployeeDTO {
    private Long id;

    private String name;

    private Integer age;

    private String email;

    private LocalDate dateOfJoining;

    private Boolean isActive;

    private String role;

    private Double salary;

    public EmployeeDTO(String name, Integer age, String email, LocalDate dateOfJoining, Boolean isActive, String role, Double salary) {
        this.name = name;
        this.age = age;
        this.email = email;
        this.dateOfJoining = dateOfJoining;
        this.isActive = isActive;
        this.role = role;
        this.salary = salary;
    }
}
