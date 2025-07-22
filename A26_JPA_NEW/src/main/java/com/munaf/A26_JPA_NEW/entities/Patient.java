package com.munaf.A26_JPA_NEW.entities;

import com.munaf.A26_JPA_NEW.enums.BloodGroup;
import jakarta.persistence.*;
import lombok.Data;
import lombok.ToString;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Data
@ToString
@Entity
public class Patient {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String address;

    @Enumerated(value = EnumType.STRING)
    private BloodGroup bloodGroup;

    @CreationTimestamp
    private LocalDateTime createdAt;

    @OneToOne()
    @JoinColumn(name = "insurance_id")
    private Insurance insurance; // owning side


    @OneToMany(mappedBy = "patient") // inverse side
    private Set<Appointment> appointments = new HashSet<>();

}
