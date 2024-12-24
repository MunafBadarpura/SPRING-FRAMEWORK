package com.munaf.A09_SPRING_WEB_3.annotations.PasswordCheckAnnotation;


import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = PasswordCheckValidator.class)
public @interface PasswordCheckValidation {
    String message() default "Password should contain one uppercase, one lowercase, one number, one special char and minimum 8digit ";

    Class<?>[] groups() default { };

    Class<? extends Payload>[] payload() default { };

}
