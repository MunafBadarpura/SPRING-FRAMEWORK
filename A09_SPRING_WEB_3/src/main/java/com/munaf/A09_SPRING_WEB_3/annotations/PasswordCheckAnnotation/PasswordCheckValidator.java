package com.munaf.A09_SPRING_WEB_3.annotations.PasswordCheckAnnotation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class PasswordCheckValidator implements ConstraintValidator<PasswordCheckValidation, String> {
    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {

        if (value == null || value.isEmpty()) {
            return false; // Null or empty passwords are invalid
        }

        String regex = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,}$";
        if (value.matches(regex)) return true;
        else return false;

    }
}
