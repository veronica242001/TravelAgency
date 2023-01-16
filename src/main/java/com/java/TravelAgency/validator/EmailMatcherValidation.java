package com.java.TravelAgency.validator;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class EmailMatcherValidation implements ConstraintValidator<EmailMatcher, String> {

@Override
public boolean isValid(String value, ConstraintValidatorContext constraintValidatorContext) {
        if(value == null)
        return false;
        return value.matches("^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$"); // regex for email
        }
        }