package com.munaf.A09_SPRING_WEB_3.annotations.EmployeeRoleAnnotation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.FIELD,ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = EmployeeRoleValidator.class)
public @interface EmployeeRoleValidation {
    String message() default "Role Of employee either be ADMIN or USER";

    Class<?>[] groups() default { };

    Class<? extends Payload>[] payload() default { };
}
