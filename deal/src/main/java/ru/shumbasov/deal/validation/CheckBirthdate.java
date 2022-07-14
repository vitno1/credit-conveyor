package ru.shumbasov.conveyor.validation;


import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.time.LocalDate;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = CheckBirthdateValidator.class)
public @interface CheckBirthdate {
    public String message() default "age must be at least 18 years old";

    public Class<?>[] groups() default {};

    public Class<? extends Payload>[] payload() default {};
}
