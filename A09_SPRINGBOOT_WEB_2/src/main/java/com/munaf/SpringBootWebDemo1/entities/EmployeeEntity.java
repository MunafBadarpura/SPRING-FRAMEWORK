package com.munaf.SpringBootWebDemo1.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "Employees")
public class EmployeeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String name;
    private int age;
    private boolean isActive;



}
