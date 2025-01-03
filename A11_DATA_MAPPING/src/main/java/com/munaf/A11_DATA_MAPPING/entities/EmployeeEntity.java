package com.munaf.A11_DATA_MAPPING.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "employees")
public class EmployeeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @OneToOne(mappedBy = "manager")
    @JsonIgnore
    private DepartmentEntity managedDepartment;

    @ManyToOne(cascade = CascadeType.ALL)      // foreign key
    @JoinColumn(name = "department_id", referencedColumnName = "id")
    @JsonIgnore
    private DepartmentEntity workDepartment;

    @ManyToMany
    @JoinTable( // create a table
            name = "department_freelancer_table",
            joinColumns = @JoinColumn(name = "employee_id"),
            inverseJoinColumns = @JoinColumn(name = "department_id")
    )
    @JsonIgnore
    private List<DepartmentEntity> freelancerDepartment;



//    @Override
//    public boolean equals(Object o) {
//        if (this == o) return true;
//        if (!(o instanceof EmployeeEntity that)) return false;
//        return Objects.equals(getId(), that.getId()) && Objects.equals(getName(), that.getName());
//    }
//
//    @Override
//    public int hashCode() {
//        return Objects.hash(getId(), getName());
//    }
}
