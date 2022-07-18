package ru.shumbasov.conveyor.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import ru.shumbasov.conveyor.enums.Gender;
import ru.shumbasov.conveyor.enums.MaritalStatus;
import ru.shumbasov.conveyor.validation.CheckBirthdate;

import javax.validation.constraints.*;
import java.math.BigDecimal;
import java.time.LocalDate;


public class ScoringDataDTO {

    @DecimalMin(value = "10000", message = "minimal value is 10000")
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

    public ScoringDataDTO() {
    }

    public ScoringDataDTO(BigDecimal amount, Integer term, String firstName,
                          String lastName, String middleName, Gender gender,
                          LocalDate birthdate, String passportSeries, String passportNumber,
                          LocalDate passportIssueDate, String passportIssueBranch, MaritalStatus maritalStatus,
                          Integer dependentAmount, EmploymentDTO employment, String account,
                          Boolean isInsuranceEnabled, Boolean isSalaryClient) {
        this.amount = amount;
        this.term = term;
        this.firstName = firstName;
        this.lastName = lastName;
        this.middleName = middleName;
        this.gender = gender;
        this.birthdate = birthdate;
        this.passportSeries = passportSeries;
        this.passportNumber = passportNumber;
        this.passportIssueDate = passportIssueDate;
        this.passportIssueBranch = passportIssueBranch;
        this.maritalStatus = maritalStatus;
        this.dependentAmount = dependentAmount;
        this.employment = employment;
        this.account = account;
        this.isInsuranceEnabled = isInsuranceEnabled;
        this.isSalaryClient = isSalaryClient;
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

    public Enum getGender() {
        return gender;
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

    public LocalDate getPassportIssueDate() {
        return passportIssueDate;
    }

    public void setPassportIssueDate(LocalDate passportIssueDate) {
        this.passportIssueDate = passportIssueDate;
    }

    public String getPassportIssueBranch() {
        return passportIssueBranch;
    }

    public void setPassportIssueBranch(String passportIssueBranch) {
        this.passportIssueBranch = passportIssueBranch;
    }

    public Enum getMaritalStatus() {
        return maritalStatus;
    }


    public Integer getDependentAmount() {
        return dependentAmount;
    }

    public void setDependentAmount(Integer dependentAmount) {
        this.dependentAmount = dependentAmount;
    }

    public EmploymentDTO getEmployment() {
        return employment;
    }

    public void setEmployment(EmploymentDTO employment) {
        this.employment = employment;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public Boolean getIsInsuranceEnabled() {
        return isInsuranceEnabled;
    }

    public void setIsInsuranceEnabled(Boolean insuranceEnabled) {
        isInsuranceEnabled = insuranceEnabled;
    }

    public Boolean getIsSalaryClient() {
        return isSalaryClient;
    }

    public void setIsSalaryClient(Boolean salaryClient) {
        isSalaryClient = salaryClient;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public void setMaritalStatus(MaritalStatus maritalStatus) {
        this.maritalStatus = maritalStatus;
    }
}