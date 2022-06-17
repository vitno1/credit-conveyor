package ru.shumbasov.conveyor.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import ru.shumbasov.conveyor.dto.ScoringDataDTO;
import ru.shumbasov.conveyor.enums.EmploymentStatus;
import ru.shumbasov.conveyor.enums.Gender;
import ru.shumbasov.conveyor.enums.MaritalStatus;
import ru.shumbasov.conveyor.enums.Position;
import ru.shumbasov.conveyor.exception.ScoringException;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;

@Service
public class ScoringService {
    private String errorMessage;
    private BigDecimal rate;

    private long calculateAge(ScoringDataDTO scoringDataDTO) {
        LocalDate start = scoringDataDTO.getBirthdate();
        LocalDate stop = LocalDate.now(ZoneId.of("Asia/Yerevan"));
        long years = java.time.temporal.ChronoUnit.YEARS.between(start, stop);
        return years;

    }

    public void checkAndScoreData(ScoringDataDTO scoringDataDTO) {
        this.errorMessage = "";
        this.rate = new BigDecimal("1.21");
        System.out.println("Залетаем в checkAndScore");
        System.out.println("scoringDataDTO.getAmount() " + scoringDataDTO.getAmount() + " scoringDataDTO.getTerm() " + scoringDataDTO.getTerm());
        System.out.println(scoringDataDTO.getFirstName() + scoringDataDTO.getLastName() + scoringDataDTO.getMiddleName());
        System.out.println("scoringDataDTO.getGender() " + scoringDataDTO.getGender() + " ДР " + scoringDataDTO.getBirthdate() + " серия паспорта " + scoringDataDTO.getPassportSeries()
                + "номер паспорта" + scoringDataDTO.getPassportNumber());
        System.out.println("кем и когда выдан" + scoringDataDTO.getPassportIssueBranch() + " " + scoringDataDTO.getPassportIssueDate());
        System.out.println("женат/замужем " + scoringDataDTO.getMaritalStatus() + " кол-во иждивенцев " + scoringDataDTO.getDependentAmount());
        System.out.println("scoringDataDTO.getEmployment().getEmploymentStatus() " + scoringDataDTO.getEmployment().getEmploymentStatus() +
                scoringDataDTO.getEmployment().getEmployerINN() + scoringDataDTO.getEmployment().getSalary()
                + scoringDataDTO.getEmployment().getPosition() + scoringDataDTO.getEmployment().getWorkExperienceTotal() + scoringDataDTO.getEmployment().getWorkExperienceCurrent());
        System.out.println("аккаунт " + scoringDataDTO.getAccount() + " страховка " + scoringDataDTO.getIsInsuranceEnabled() + " зп карта клиента " + scoringDataDTO.getIsSalaryClient());


        Enum employmentStatus = scoringDataDTO.getEmployment().getEmploymentStatus();
        if (employmentStatus == EmploymentStatus.BUSINESS_OWNER) {
            this.rate = rate.add(new BigDecimal("0.03"));

        } else if (employmentStatus == EmploymentStatus.SELF_EMPLOYED) {
            this.rate = rate.add(new BigDecimal("0.01"));
        } else if (employmentStatus == EmploymentStatus.UNEMPLOYED) {
            this.errorMessage += "We do not give loans to the unemployed\n";
        } else {
            this.errorMessage += "Enter work status correctly\n";
        }


        if (scoringDataDTO.getAmount().compareTo(scoringDataDTO.getEmployment().getSalary().multiply(new BigDecimal("20"))) == 1 ||
                scoringDataDTO.getAmount().compareTo(scoringDataDTO.getEmployment().getSalary().multiply(new BigDecimal("20"))) == 0) {
            this.errorMessage += "You have a very small salary\n";
        }

        long years = calculateAge(scoringDataDTO);
        System.out.println("Возраст = " + years);

        if (years < 18 || years > 60) {
            this.errorMessage += "Age entered incorrectly\n";

        }

        if (scoringDataDTO.getEmployment().getWorkExperienceCurrent() < 3) {
            this.errorMessage += "Your current experience is low\n";
        }

        if (scoringDataDTO.getEmployment().getWorkExperienceTotal() < 12) {
            this.errorMessage += "Your total experience is low";
        }
        Enum position = scoringDataDTO.getEmployment().getPosition();
        if (position == Position.ENGINEER) {
            this.rate = rate.subtract(new BigDecimal("0.02"));
        } else if (position == Position.MANAGER) {
            this.rate = rate.subtract(new BigDecimal("0.04"));
        }

        Enum maritalStatus = scoringDataDTO.getMaritalStatus();
        if (maritalStatus == MaritalStatus.IS_MARRIED) {
            this.rate = rate.subtract(new BigDecimal("0.03"));
        } else if (maritalStatus == MaritalStatus.IS_NOT_MARRIED) {
            this.rate = rate.add(new BigDecimal("0.01"));
        }

        if (scoringDataDTO.getDependentAmount() > 1) {
            this.rate = rate.add(new BigDecimal("0.01"));
        }

        if (scoringDataDTO.getGender() == Gender.IS_MALE && years >= 30 && years <= 55) {
            this.rate = rate.subtract(new BigDecimal("0.03"));
        }
        if (scoringDataDTO.getGender() == Gender.IS_FEMALE && years >= 35 && years <= 60) {
            this.rate = rate.subtract(new BigDecimal("0.03"));
        }
        System.out.println("первый вывод rate " + rate);
        checkException();
        System.out.println("rate again " + rate);


    }

    private void checkException() {
        if (!errorMessage.isEmpty()) {
//            throw new ScoringException(errorList);
            throw new ScoringException(errorMessage);
        }
    }


    public ScoringService() {
    }

    public BigDecimal getRate() {
        return rate;
    }
}
