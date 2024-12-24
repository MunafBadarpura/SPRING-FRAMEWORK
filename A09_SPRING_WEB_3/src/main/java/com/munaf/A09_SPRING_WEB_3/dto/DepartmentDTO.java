package com.munaf.A09_SPRING_WEB_3.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.munaf.A09_SPRING_WEB_3.annotations.PasswordCheckAnnotation.PasswordCheckValidation;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.URL;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DepartmentDTO {
    private Long id;

    @NotBlank(message = "Title can not be blank")
    @Size(min = 3, max = 15, message = "Title can be in the range of 3 to 15")
    private String title;

    @AssertTrue(message = "Department always be active")
    @NotNull(message = "isActive can not be null")
    private Boolean isActive;

    @PastOrPresent(message = "Created it can be past or present")
    @NotNull(message = "createdAt can not be null")
    private LocalDate createdAt;

    @Email(message = "Email is not valid")
    @NotBlank(message = "departmentEmail can not be blank")
    private String departmentEmail;

    @URL(message = "url is not valid")
    @NotBlank(message = "departmentURL can not be blank")
    private String departmentURL;

    @PasswordCheckValidation
    private String departmentPassword;
}
