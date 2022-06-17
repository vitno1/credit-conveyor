package ru.shumbasov.conveyor.dto;

import ru.shumbasov.conveyor.enums.EmploymentStatus;
import ru.shumbasov.conveyor.enums.Position;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;


public class EmploymentDTO {


    @NotNull
    private EmploymentStatus employmentStatus;

    private String employerINN;

    private BigDecimal salary;

    @NotNull
    private Position position;

    private Integer workExperienceTotal;

    private Integer workExperienceCurrent;

    public Enum getEmploymentStatus() {
        return employmentStatus;
    }

    public void setEmploymentStatus(EmploymentStatus employmentStatus) {
        this.employmentStatus = employmentStatus;
    }

    public String getEmployerINN() {
        return employerINN;
    }

    public void setEmployerINN(String employerINN) {
        this.employerINN = employerINN;
    }

    public BigDecimal getSalary() {
        return salary;
    }

    public void setSalary(BigDecimal salary) {
        this.salary = salary;
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    public Integer getWorkExperienceTotal() {
        return workExperienceTotal;
    }

    public void setWorkExperienceTotal(Integer workExperienceTotal) {
        this.workExperienceTotal = workExperienceTotal;
    }

    public Integer getWorkExperienceCurrent() {
        return workExperienceCurrent;
    }

    public void setWorkExperienceCurrent(Integer workExperienceCurrent) {
        this.workExperienceCurrent = workExperienceCurrent;
    }

    public EmploymentDTO(EmploymentStatus employmentStatus, String employerINN, BigDecimal salary, Position position, Integer workExperienceTotal, Integer workExperienceCurrent) {
        this.employmentStatus = employmentStatus;
        this.employerINN = employerINN;
        this.salary = salary;
        this.position = position;
        this.workExperienceTotal = workExperienceTotal;
        this.workExperienceCurrent = workExperienceCurrent;
    }

    public EmploymentDTO() {
    }
}
