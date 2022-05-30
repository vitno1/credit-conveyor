package ru.shumbasov.conveyor.dto;

import java.math.BigDecimal;

/*
 * employment - трудоустройство ,работа,использование,применение,занятие,наем,служба */
public class EmploymentDTO {

    private Enum employmentStatus;
    private String employerINN;
    private BigDecimal salary;
    private Enum position;
    private Integer workExperienceTotal;
    private Integer workExperienceCurrent;


}
