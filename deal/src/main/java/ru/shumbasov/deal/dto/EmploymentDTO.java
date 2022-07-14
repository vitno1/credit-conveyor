package ru.shumbasov.deal.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.shumbasov.deal.enums.EmploymentStatus;
import ru.shumbasov.deal.enums.Position;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmploymentDTO {
    @NotNull
    private EmploymentStatus employmentStatus;

    private String employerINN;

    private BigDecimal salary;

    @NotNull
    private Position position;

    private Integer workExperienceTotal;

    private Integer workExperienceCurrent;
}
