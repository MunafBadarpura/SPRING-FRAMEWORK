package com.munaf.A09_SPRING_WEB_3.annotations.PrimeNumberAnnotation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;


public class PrimeNumberValidator implements ConstraintValidator<PrimeNumberValidation, Integer> {

    @Override
    public boolean isValid(Integer inputValue, ConstraintValidatorContext context) {
        if (inputValue == 1 || inputValue == 0) return false;
        for (int i=2;i<inputValue;i++){
            if (inputValue%i == 0) return false;
        }
        return true;
    }
}
