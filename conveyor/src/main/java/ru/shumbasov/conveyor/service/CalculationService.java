package ru.shumbasov.conveyor.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.shumbasov.conveyor.dto.CreditDTO;
import ru.shumbasov.conveyor.dto.PaymentScheduleElement;
import ru.shumbasov.conveyor.dto.ScoringDataDTO;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.time.Duration;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class CalculationService {
    private ScoringDataDTO scoringDataDTO;
    private final ScoringService scoringService;

    public void checkScoring() {
        log.info("Start check and scoring data");
        scoringService.checkAndScoreData(scoringDataDTO);
    }

    public CreditDTO getCreditDTO() {
        CreditDTO creditDTO = new CreditDTO();
        assignAmount(creditDTO);
        creditDTO.setTerm(scoringDataDTO.getTerm());
        creditDTO.setRate(scoringService.getRate());
        creditDTO.setInsuranceEnabled(scoringDataDTO.getIsInsuranceEnabled());
        creditDTO.setSalaryClient(scoringDataDTO.getIsSalaryClient());
        calculateMonthlyPayment(creditDTO);
        creditDTO.setPaymentSchedule(assignPaymentSchedule(creditDTO));
        creditDTO.setPsk(calculatePSK(creditDTO));
        return creditDTO;

    }

    private void calculateMonthlyPayment(CreditDTO creditDTO) {
        log.info("Start calculating MonthlyPayment");
        BigDecimal monthlyPercentRate = creditDTO.getRate().subtract(new BigDecimal("1")).divide(new BigDecimal("12"), MathContext.UNLIMITED);
        BigDecimal monthlyPercentRatePlusOne = monthlyPercentRate.add(new BigDecimal("1")).pow(creditDTO.getTerm());
        BigDecimal left = monthlyPercentRate.multiply(monthlyPercentRatePlusOne);
        BigDecimal right = monthlyPercentRatePlusOne.subtract(new BigDecimal("1"));
        BigDecimal annuityRatio = left.divide(right, RoundingMode.HALF_UP);
        log.info("annuityRatio is " + annuityRatio);
        BigDecimal monthlyPayment = creditDTO.getAmount().multiply(annuityRatio, MathContext.DECIMAL32);
        log.info("monthlyPayment is " + monthlyPayment);
        creditDTO.setMonthlyPayment(monthlyPayment);
    }

    private List<PaymentScheduleElement> assignPaymentSchedule(CreditDTO creditDTO) {
        log.info("Start calculating PaymentScheduleElement");
        List<PaymentScheduleElement> result = new ArrayList<>();
        int j = 0;
        int k = 0;
        BigDecimal remainingDebt = new BigDecimal("0");
        for (int i = 0; i < scoringDataDTO.getTerm(); i++) {
            PaymentScheduleElement paymentScheduleElement = new PaymentScheduleElement();
            paymentScheduleElement.setRemainingDebt(remainingDebt);
            if (i == 0) {
                paymentScheduleElement.setRemainingDebt(creditDTO.getAmount());
            }
            paymentScheduleElement.setTotalPayment(creditDTO.getMonthlyPayment());
            paymentScheduleElement.setNumber(++k);
            paymentScheduleElement.setDate(LocalDate.now().plusMonths(++j));
            BigDecimal percent = paymentScheduleElement.getRemainingDebt().multiply(creditDTO.getRate().subtract(new BigDecimal("1"))).
                    multiply(BigDecimal.valueOf(LocalDate.now().plusMonths(i).lengthOfMonth())).
                    divide(BigDecimal.valueOf(LocalDate.now().plusMonths(i).lengthOfYear()), RoundingMode.HALF_UP);
            paymentScheduleElement.setInterestPayment(percent);
            paymentScheduleElement.setDebtPayment(paymentScheduleElement.getTotalPayment().subtract(percent));
            paymentScheduleElement.setRemainingDebt(paymentScheduleElement.getRemainingDebt().subtract(paymentScheduleElement.getDebtPayment()));
            remainingDebt = paymentScheduleElement.getRemainingDebt();
            result.add(paymentScheduleElement);
        }
        return result;
    }

    private BigDecimal calculatePSK(CreditDTO creditDTO) {
        List<LocalDate> dates = new ArrayList<>();
        List<BigDecimal> payments = new ArrayList<>();
        BigDecimal basePeriod = new BigDecimal(30);
        BigDecimal countBasePeriods = new BigDecimal(365).divide(basePeriod, 3, RoundingMode.HALF_UP);
        List<PaymentScheduleElement> paymentSchedule = creditDTO.getPaymentSchedule();
        for (int i = 0; i < paymentSchedule.size(); i++) {
            dates.add(paymentSchedule.get(i).getDate());
            payments.add(paymentSchedule.get(i).getTotalPayment());
        }
        dates.add(0, LocalDate.now());
        payments.add(0, creditDTO.getAmount().negate());
        System.out.println(dates);
        System.out.println(payments);
        System.out.println(dates.size() + "   " + payments.size());

        List<BigDecimal> differenceDayStartLoanAndDayPayment = new ArrayList<>();
        for (int i = 0; i < dates.size(); i++) {
            long difference = Duration.between(dates.get(0).atStartOfDay(), dates.get(i).atStartOfDay()).toDays();
            differenceDayStartLoanAndDayPayment.add(new BigDecimal(difference));
        }
        List<BigDecimal> eList = new ArrayList<>();
        List<BigDecimal> qList = new ArrayList<>();
        System.out.println("differenceDayStartLoanAndDayPayment.size() = " + differenceDayStartLoanAndDayPayment.size());

        for (int i = 0; i < differenceDayStartLoanAndDayPayment.size(); i++) {
            eList.add(differenceDayStartLoanAndDayPayment.get(i).remainder(new BigDecimal(30)).divide(new BigDecimal(30), 3, RoundingMode.HALF_UP));
            qList.add(differenceDayStartLoanAndDayPayment.get(i).divide(new BigDecimal(30), 3, RoundingMode.HALF_UP));
        }

        BigDecimal i = new BigDecimal(0);
        BigDecimal sum = new BigDecimal(1);

        while (sum.doubleValue() > 0) {
            sum = new BigDecimal(0);
            for (int k = 0; k < eList.size(); k++) {
                BigDecimal left = eList.get(k).multiply(i).add(new BigDecimal(1));
                BigDecimal right = (new BigDecimal(1).add(i)).pow(qList.get(k).intValue());
                sum = payments.get(k).divide((left.multiply(right, MathContext.DECIMAL64)), 3, RoundingMode.HALF_EVEN).add(sum);
            }
            i = i.add(new BigDecimal("0.000007") ,  MathContext.DECIMAL32);

        }
        BigDecimal psk = i.multiply(countBasePeriods).multiply(new BigDecimal(100));


        return psk;
    }

    private void assignAmount(CreditDTO creditDTO) {
        if (scoringDataDTO.getIsInsuranceEnabled()) {
            creditDTO.setAmount(scoringDataDTO.getAmount().multiply(new BigDecimal("1.1")));
        } else {
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
