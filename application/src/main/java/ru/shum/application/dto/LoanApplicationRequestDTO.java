package ru.shum.application.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.shum.application.validation.CheckBirthdate;

import javax.validation.constraints.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LoanApplicationRequestDTO {
    @DecimalMin(value = "10000", message = "minimal value is 10000")
    @Digits(integer = Integer.MAX_VALUE, fraction = 0)
    private BigDecimal amount;

    @Min(value = 6, message = "minimal value is 6")
    private Integer term;

    @Size(min = 2, max = 30, message = "the size of this field is between 2 and 30 characters")
    @Pattern(regexp = "^[a-zA-Z]+$", message = "Only latin characters")
    private String firstName;

    @Size(min = 2, max = 30, message = "the size of this field is between 2 and 30 characters")
    @Pattern(regexp = "^[a-zA-Z]+$", message = "Only latin characters")
    private String lastName;

    @Size(min = 2, max = 30, message = "the size of this field is between 2 and 30 characters")
    @Pattern(regexp = "^[a-zA-Z]+$", message = "Only latin characters")
    private String middleName;

    @Email
    private String email;

    @CheckBirthdate
    private LocalDate birthdate;

    @Pattern(regexp = "\\d{4}", message = "please use pattern XXXX")
    private String passportSeries;

    @Pattern(regexp = "\\d{6}", message = "please use pattern XXXXXX")
    private String passportNumber;
}
