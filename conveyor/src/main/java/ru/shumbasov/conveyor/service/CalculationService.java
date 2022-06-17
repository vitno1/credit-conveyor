package ru.shumbasov.conveyor.service;

import org.springframework.stereotype.Service;
import ru.shumbasov.conveyor.dto.CreditDTO;
import ru.shumbasov.conveyor.dto.PaymentScheduleElement;
import ru.shumbasov.conveyor.dto.ScoringDataDTO;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.util.List;

@Service
public class CalculationService {
    private ScoringDataDTO scoringDataDTO;
    private final ScoringService scoringService;

    public void checkScoring() {
        System.out.println("Залетели в checkScoring который метод CalculationService");
        scoringService.checkAndScoreData(scoringDataDTO);
    }

    public CreditDTO assignCreditDTO() {
        CreditDTO creditDTO = new CreditDTO();
        assignAmount(creditDTO);
        creditDTO.setTerm(scoringDataDTO.getTerm());
        creditDTO.setRate(scoringService.getRate());
        creditDTO.setInsuranceEnabled(scoringDataDTO.getIsInsuranceEnabled());
        creditDTO.setSalaryClient(scoringDataDTO.getIsSalaryClient());
        calculateMonthlyPayment(creditDTO);
        return creditDTO;

    }

    private void calculateMonthlyPayment(CreditDTO creditDTO){
        System.out.println("Ставка в десятичных " + creditDTO.getRate());
        BigDecimal monthlyPercentRate = creditDTO.getRate().subtract(new BigDecimal("1")).divide(new BigDecimal("12") , MathContext.UNLIMITED);
        System.out.println("Ежемесячная ставка " + monthlyPercentRate);
        BigDecimal monthlyPercentRatePlusOne = monthlyPercentRate.add(new BigDecimal("1")).pow(creditDTO.getTerm());
        System.out.println(monthlyPercentRatePlusOne);
        BigDecimal left = monthlyPercentRate.multiply(monthlyPercentRatePlusOne);
        System.out.println("left is " + left);
        BigDecimal right = monthlyPercentRatePlusOne.subtract(new BigDecimal("1"));
        System.out.println("right is " + right);
        BigDecimal annuityRatio = left.divide(right , RoundingMode.HALF_UP);
        BigDecimal monthlyPayment = creditDTO.getAmount().multiply(annuityRatio , MathContext.DECIMAL32);
        System.out.println("annuityRatio = " + annuityRatio);
        System.out.println(monthlyPayment);
    }

    private List<PaymentScheduleElement> assignPaymentSchedule(){


     return null;
    }

    private void assignAmount(CreditDTO creditDTO) {
        if (scoringDataDTO.getIsInsuranceEnabled()) {
            creditDTO.setAmount(scoringDataDTO.getAmount().multiply(new BigDecimal("1.1")));
        }else{
            creditDTO.setAmount(scoringDataDTO.getAmount());
        }
    }


    public CalculationService(ScoringService scoringService) {
        this.scoringService = scoringService;
    }


    public void setScoringDataDTO(ScoringDataDTO scoringDataDTO) {
        this.scoringDataDTO = scoringDataDTO;
    }
}
