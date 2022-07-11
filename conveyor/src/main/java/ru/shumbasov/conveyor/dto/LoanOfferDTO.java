package ru.shumbasov.conveyor.dto;

import java.math.BigDecimal;

public class LoanOfferDTO {

    private Long applicationId;
    private BigDecimal requestedAmount;
    private BigDecimal totalAmount;
    private Integer term;
    private BigDecimal monthlyPayment;
    private BigDecimal rate;
    private Boolean isInsuranceEnabled;
    private Boolean isSalaryClient;

    public LoanOfferDTO() {
    }

    public Long getApplicationId() {
        return applicationId;
    }

    public void setApplicationId(Long applicationId) {
        this.applicationId = applicationId;
    }

    public BigDecimal getRequestedAmount() {
        return requestedAmount;
    }

    public void setRequestedAmount(BigDecimal requestedAmount) {
        this.requestedAmount = requestedAmount;
    }

    public BigDecimal getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(BigDecimal totalAmount) {
        this.totalAmount = totalAmount;
    }

    public Integer getTerm() {
        return term;
    }

    public void setTerm(Integer term) {
        this.term = term;
    }

    @Override
    public String toString() {
        return "LoanOfferDTO{" +
                "applicationId=" + applicationId +
                ", requestedAmount=" + requestedAmount +
                ", totalAmount=" + totalAmount +
                ", term=" + term +
                ", monthlyPayment=" + monthlyPayment +
                ", rate=" + rate +
                ", isInsuranceEnabled=" + isInsuranceEnabled +
                ", isSalaryClient=" + isSalaryClient +
                '}';
    }

    public BigDecimal getMonthlyPayment() {
        return monthlyPayment;
    }

    public void setMonthlyPayment(BigDecimal monthlyPayment) {
        this.monthlyPayment = monthlyPayment;
    }

    public BigDecimal getRate() {
        return rate;
    }

    public void setRate(BigDecimal rate) {
        this.rate = rate;
    }

    public Boolean getInsuranceEnabled() {
        return isInsuranceEnabled;
    }

    public void setInsuranceEnabled(Boolean insuranceEnabled) {
        isInsuranceEnabled = insuranceEnabled;
    }

    public Boolean getSalaryClient() {
        return isSalaryClient;
    }

    public void setSalaryClient(Boolean salaryClient) {
        isSalaryClient = salaryClient;
    }
}
