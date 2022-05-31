package ru.shumbasov.conveyor.dto;


import org.springframework.format.annotation.DateTimeFormat;
import javax.validation.constraints.*;

import java.math.BigDecimal;
import java.time.LocalDate;
/*
 * заявка на получение кредита*/


public class LoanApplicationRequestDTO {


    @DecimalMin(value = "10000", message = "minimal value is 10000")
    @Digits(integer = Integer.MAX_VALUE, fraction = 0)
    private BigDecimal amount; //сумма

    @Min(value = 6, message = "minimal value is 6")
    private Integer term; //срок

    @Size(min = 2, max = 30, message = "the size of this field is between 2 and 30 characters")
    //Завалидировать все поля так чтобы только латинские символы
    private String firstName;

    @Size(min = 2, max = 30, message = "the size of this field is between 2 and 30 characters")
    private String lastName;

    @Size(min = 2, max = 30, message = "the size of this field is between 2 and 30 characters")
    private String middleName;

    @Email
    private String email;

    @Pattern(regexp = "\\d{4}-\\d{2}-\\d{2}", message = "please use pattern XXXX.XX.XX")
//    @DateTimeFormat(pattern = "yyyy.MM.dd")
    private LocalDate birthdate;


    @Pattern(regexp = "\\d{4}", message = "please use pattern XXXX")
    private String passportSeries;

    @Pattern(regexp = "\\d{6}", message = "please use pattern XXXXXX")
    private String passportNumber;

    public LoanApplicationRequestDTO() {
    }

    public LoanApplicationRequestDTO(BigDecimal amount,
                                     Integer term,
                                     String firstName,
                                     String lastName,
                                     String middleName,
                                     String email,
                                     LocalDate birthdate,
                                     String passportSeries,
                                     String passportNumber) {
        this.amount = amount;
        this.term = term;
        this.firstName = firstName;
        this.lastName = lastName;
        this.middleName = middleName;
        this.email = email;
        this.birthdate = birthdate;
        this.passportSeries = passportSeries;
        this.passportNumber = passportNumber;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public Integer getTerm() {
        return term;
    }

    public void setTerm(Integer term) {
        this.term = term;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public LocalDate getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(LocalDate birthdate) {
        this.birthdate = birthdate;
    }

    public String getPassportSeries() {
        return passportSeries;
    }

    public void setPassportSeries(String passportSeries) {
        this.passportSeries = passportSeries;
    }

    public String getPassportNumber() {
        return passportNumber;
    }

    public void setPassportNumber(String passportNumber) {
        this.passportNumber = passportNumber;
    }
}
