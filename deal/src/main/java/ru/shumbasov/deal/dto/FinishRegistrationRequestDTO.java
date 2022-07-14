package ru.shumbasov.deal.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.shumbasov.deal.enums.Gender;
import ru.shumbasov.deal.enums.MaritalStatus;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FinishRegistrationRequestDTO {

    @NotNull
    private Gender gender;
    @NotNull
    private MaritalStatus maritalStatus;
    @NotNull
    private Integer dependentAmount;
    @NotNull
    private LocalDate passportIssueDate;
    @NotNull
    private String passportIssueBranch;
    @NotNull
    private EmploymentDTO employment;
    @NotNull
    private String account;


}
