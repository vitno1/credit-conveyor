package ru.shumbasov.conveyor.service;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ru.shumbasov.conveyor.dto.EmploymentDTO;
import ru.shumbasov.conveyor.dto.ScoringDataDTO;
import ru.shumbasov.conveyor.enums.EmploymentStatus;
import ru.shumbasov.conveyor.enums.Gender;
import ru.shumbasov.conveyor.enums.MaritalStatus;
import ru.shumbasov.conveyor.enums.Position;
import ru.shumbasov.conveyor.exception.ScoringException;

import java.math.BigDecimal;
import java.time.LocalDate;

@SpringBootTest
public class ScoringServiceTest {
    private ScoringDataDTO scoringDataDTO;
    private EmploymentDTO employmentDTO;

    @Autowired
    ScoringService scoringService;

    @BeforeEach
    void prepare() {
        employmentDTO = new EmploymentDTO();
        scoringDataDTO = new ScoringDataDTO();
        employmentDTO.setSalary(new BigDecimal("30000"));
        scoringDataDTO.setAmount(new BigDecimal("10000"));
        scoringDataDTO.setBirthdate(LocalDate.of(1983 , 04, 16));
        employmentDTO.setWorkExperienceCurrent(5);
        employmentDTO.setWorkExperienceTotal(16);
        scoringDataDTO.setDependentAmount(10000);
        scoringDataDTO.setEmployment(employmentDTO);


    }

    @Test
    void employmentStatusCorrectlyIfBusinessOwner() {
        employmentDTO.setEmploymentStatus(EmploymentStatus.BUSINESS_OWNER);
        scoringService.checkAndScoreData(scoringDataDTO);
        final BigDecimal rate = scoringService.getRate();
        assertThat(rate).isEqualTo(new BigDecimal("1.24"));
    }

    @Test
    void employmentStatusCorrectlyIfSelfEmployed() {
        employmentDTO.setEmploymentStatus(EmploymentStatus.SELF_EMPLOYED);
        scoringService.checkAndScoreData(scoringDataDTO);
        final BigDecimal rate = scoringService.getRate();
        assertThat(rate).isEqualTo(new BigDecimal("1.22"));
    }
    @Test
    void employmentStatusCorrectlyIfSelfEUnEmployed() {
        employmentDTO.setEmploymentStatus(EmploymentStatus.UNEMPLOYED);
        Assertions.assertThrows(ScoringException.class , () -> scoringService.checkAndScoreData(scoringDataDTO));
    }


    @Test
    void smallSalary(){
        employmentDTO.setSalary(new BigDecimal("0"));
        Assertions.assertThrows(ScoringException.class , () -> scoringService.checkAndScoreData(scoringDataDTO));
    }

    @Test
    void correctlyAge(){
        scoringDataDTO.setBirthdate(LocalDate.of(2020 , 11, 16));
        Assertions.assertThrows(ScoringException.class , () -> scoringService.checkAndScoreData(scoringDataDTO));
    }
    @Test
    void correctlyWorkExperienceCurrent(){
        employmentDTO.setWorkExperienceCurrent(1);
        Assertions.assertThrows(ScoringException.class , () -> scoringService.checkAndScoreData(scoringDataDTO));
    }

    @Test
    void correctlyWorkExperienceTotal(){
        employmentDTO.setWorkExperienceTotal(4);
        Assertions.assertThrows(ScoringException.class , () -> scoringService.checkAndScoreData(scoringDataDTO));
    }

    @Test
    void correctlyPositionIfEngineer(){
        employmentDTO.setPosition(Position.ENGINEER);
        scoringService.checkAndScoreData(scoringDataDTO);
        final BigDecimal rate = scoringService.getRate();
        assertThat(rate).isEqualTo(new BigDecimal("1.19"));
    }

    @Test
    void correctlyPositionIfManager(){
        employmentDTO.setPosition(Position.MANAGER);
        scoringService.checkAndScoreData(scoringDataDTO);
        final BigDecimal rate = scoringService.getRate();
        assertThat(rate).isEqualTo(new BigDecimal("1.17"));
    }

    @Test
    void correctlyMaritalStatusIfIsMarried(){
     scoringDataDTO.setMaritalStatus(MaritalStatus.IS_MARRIED);
     scoringService.checkAndScoreData(scoringDataDTO);
        final BigDecimal rate = scoringService.getRate();
        assertThat(rate).isEqualTo(new BigDecimal("1.18"));
    }

    @Test
    void correctlyMaritalStatusIfIsNotMarried(){
        scoringDataDTO.setMaritalStatus(MaritalStatus.IS_NOT_MARRIED);
        scoringService.checkAndScoreData(scoringDataDTO);
        final BigDecimal rate = scoringService.getRate();
        assertThat(rate).isEqualTo(new BigDecimal("1.22"));
    }

    @Test
    void correctlyGenderIfIsMale(){
        scoringDataDTO.setGender(Gender.IS_MALE);
        scoringService.checkAndScoreData(scoringDataDTO);
        final BigDecimal rate = scoringService.getRate();
        assertThat(rate).isEqualTo(new BigDecimal("1.18"));
    }

    @Test
    void correctlyGenderIfIsFemale(){
        scoringDataDTO.setGender(Gender.IS_FEMALE);
        scoringService.checkAndScoreData(scoringDataDTO);
        final BigDecimal rate = scoringService.getRate();
        assertThat(rate).isEqualTo(new BigDecimal("1.18"));
    }

}
