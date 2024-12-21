package com.munaf.A09_SPRING_WEB_3.annotations.PrimeNumberAnnotation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = PrimeNumberValidator.class)
public @interface PrimeNumberValidation {
    String message() default "The Number is not Prime";

    Class<?>[] groups() default { };

    Class<? extends Payload>[] payload() default { };
}
