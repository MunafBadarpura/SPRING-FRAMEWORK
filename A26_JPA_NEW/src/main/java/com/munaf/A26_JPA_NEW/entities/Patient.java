package com.munaf.A26_JPA_NEW.entities;

import com.munaf.A26_JPA_NEW.enums.BloodGroup;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class Patient {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String address;

    @Enumerated(value = EnumType.STRING)
    private BloodGroup bloodGroup;

}
