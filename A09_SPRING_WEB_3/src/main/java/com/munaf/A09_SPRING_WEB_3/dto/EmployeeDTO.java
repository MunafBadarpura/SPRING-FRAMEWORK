package com.munaf.A09_SPRING_WEB_3.dto;

import com.munaf.A09_SPRING_WEB_3.annotations.EmployeeRoleAnnotation.EmployeeRoleValidation;
import com.munaf.A09_SPRING_WEB_3.annotations.PrimeNumberAnnotation.PrimeNumberValidation;
import jakarta.validation.constraints.*;
import lombok.*;
import org.hibernate.validator.constraints.Range;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeDTO {
    private Long id;

    @NotBlank(message = "Employee name must not be blank.")
    @Size(min = 3, max = 15, message = "Employee name must be between 3 and 15 characters.")
    private String name;

    @NotNull(message = "Employee age must not be null.")
    @Positive(message = "Employee age must be greater than zero.")
    @Range(min = 18, max = 80, message = "Employee age must not exceed 18 to 80 years.")
    private Integer age;

    @NotBlank(message = "Employee email must not be blank.")
    @Email(message = "Invalid email")
    private String email;

    @PastOrPresent(message = "dateOfJoining can not be future")
    private LocalDate dateOfJoining;

    @AssertTrue(message = "isActive can not be false") // AssertFalse for always false value
    private Boolean isActive;

    @NotBlank(message = "Role cannot be blank")
//    @Pattern(regexp = "^(ADMIN|USER|DEVELOPER)$") // ADMIN/USER/DEVELOPER(use chatgpt for regex)
    @EmployeeRoleValidation
    private String role;

    @NotNull(message = "Salary cannot be null")
    @Digits(integer = 6, fraction = 2 , message = "Salary can be in form XXXXXX.YY")
    @DecimalMax(value = "100000.99", message = "Salary cannot be more than 100000.99") // we use DecimalMax for point values
    private Double salary;

}
