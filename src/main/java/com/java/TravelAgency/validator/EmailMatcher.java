package com.java.TravelAgency.validator;


import com.java.TravelAgency.constants.Constants;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = {EmailMatcherValidation.class})
public @interface EmailMatcher {

    String message() default Constants.EMAIL_MATCH;

    //represents group of constraints
    Class<?>[] groups() default {};

    //represents additional information about annotation
    Class<? extends Payload>[] payload() default {};
}