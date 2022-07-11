package ru.shumbasov.conveyor.service;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ru.shumbasov.conveyor.dto.EmploymentDTO;
import ru.shumbasov.conveyor.dto.ScoringDataDTO;

import java.math.BigDecimal;
import java.time.LocalDate;

@SpringBootTest
public class CalculationServiceTest {

    private ScoringDataDTO scoringDataDTO;
    private EmploymentDTO employmentDTO;

    @Autowired
    CalculationService calculationService;

    @BeforeEach
    void prepare() {
        employmentDTO = new EmploymentDTO();
        scoringDataDTO = new ScoringDataDTO();
        employmentDTO.setSalary(new BigDecimal("30000"));
        scoringDataDTO.setAmount(new BigDecimal("10000"));
        scoringDataDTO.setBirthdate(LocalDate.of(1983, 04, 16));
        employmentDTO.setWorkExperienceCurrent(5);
        employmentDTO.setWorkExperienceTotal(16);
        scoringDataDTO.setDependentAmount(10000);
        scoringDataDTO.setIsInsuranceEnabled(false);
        scoringDataDTO.setTerm(18);
        scoringDataDTO.setEmployment(employmentDTO);


    }

    @Test
    void correctlyScoring() {
        calculationService.setScoringDataDTO(scoringDataDTO);
        calculationService.checkScoring();
        final BigDecimal rate = calculationService.getCreditDTO().getRate();
        assertThat(rate).isEqualTo(new BigDecimal("1.21"));
    }

    @Test
    void IsWorkedAssignAmountIfTrue() {
        scoringDataDTO.setIsInsuranceEnabled(true);
        calculationService.setScoringDataDTO(scoringDataDTO);
        calculationService.checkScoring();
        final BigDecimal amount = calculationService.getCreditDTO().getAmount();
        assertThat(amount).isEqualTo(new BigDecimal("11000.0"));

    }

}
