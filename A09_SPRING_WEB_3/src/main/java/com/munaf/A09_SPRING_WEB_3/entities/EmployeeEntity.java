package com.munaf.A09_SPRING_WEB_3.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity // it will define this as a table in database
@Table(name = "Employee")
public class EmployeeEntity {
    @Id // primary key for table
    @GeneratedValue(strategy = GenerationType.AUTO) // user don't have to define id
    private Long id;
    private String name;
    private Integer age;
    private String email;
    private LocalDate dateOfJoining;
    private Boolean isActive;
    private String role;
    private Double salary;
}
