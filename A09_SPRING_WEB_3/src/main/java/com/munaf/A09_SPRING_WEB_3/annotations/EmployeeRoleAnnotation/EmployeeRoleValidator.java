package com.munaf.A09_SPRING_WEB_3.annotations.EmployeeRoleAnnotation;


import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.Arrays;
import java.util.List;

public class EmployeeRoleValidator implements ConstraintValidator<EmployeeRoleValidation, String> {
    @Override
    public boolean isValid(String inputRole, ConstraintValidatorContext context) {
        List<String> employeeRoles = Arrays.asList("USER", "ADMIN");
        return employeeRoles.contains(inputRole); // if contains it return true otherwise false
    }
}
