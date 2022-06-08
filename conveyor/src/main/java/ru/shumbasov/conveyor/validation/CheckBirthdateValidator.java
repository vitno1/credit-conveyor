package ru.shumbasov.conveyor.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.time.LocalDate;
import java.time.ZoneId;

public class CheckBirthdateValidator implements ConstraintValidator<CheckBirthdate, LocalDate> {

    @Override
    public void initialize(CheckBirthdate constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(LocalDate enteredValue, ConstraintValidatorContext constraintValidatorContext) {
        LocalDate stop = LocalDate.now(ZoneId.of("Asia/Yerevan"));
        long years = java.time.temporal.ChronoUnit.YEARS.between(enteredValue, stop);
        return years >= 18;
    }
}
