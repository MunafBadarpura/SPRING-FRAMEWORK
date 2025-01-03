package com.munaf.A11_DATA_MAPPING.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "department")
public class DepartmentEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @OneToOne // foreign key
    @JoinColumn(name = "department_manager")
    private EmployeeEntity manager;

    @OneToMany(mappedBy = "workDepartment", fetch = FetchType.LAZY)
    private List<EmployeeEntity> workers;


    @ManyToMany(mappedBy = "freelancerDepartment")
    private List<EmployeeEntity> freelancers;


//    @Override
//    public boolean equals(Object o) {
//        if (this == o) return true;
//        if (!(o instanceof DepartmentEntity that)) return false;
//        return Objects.equals(getId(), that.getId()) && Objects.equals(getName(), that.getName());
//    }
//
//    @Override
//    public int hashCode() {
//        return Objects.hash(getId(), getName());
//    }
}
