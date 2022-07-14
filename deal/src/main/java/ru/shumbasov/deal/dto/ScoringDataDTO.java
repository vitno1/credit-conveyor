package ru.shumbasov.deal.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.shumbasov.deal.enums.Gender;
import ru.shumbasov.deal.enums.MaritalStatus;
import ru.shumbasov.deal.validation.CheckBirthdate;

import javax.validation.constraints.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ScoringDataDTO {
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

    @NotNull
    private Gender gender;

    @CheckBirthdate
    private LocalDate birthdate;

    @Pattern(regexp = "\\d{4}", message = "please use pattern XXXX")
    private String passportSeries;

    @Pattern(regexp = "\\d{6}", message = "please use pattern XXXXXX")
    private String passportNumber;

    private LocalDate passportIssueDate;

    private String passportIssueBranch;

    @NotNull
    private MaritalStatus maritalStatus;

    private Integer dependentAmount;

    private EmploymentDTO employment;

    private String account;

    private Boolean isInsuranceEnabled;

    private Boolean isSalaryClient;
}
