package ru.shumbasov.conveyor.service;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import ru.shumbasov.conveyor.comparator.RateComparator;
import ru.shumbasov.conveyor.dto.LoanApplicationRequestDTO;
import ru.shumbasov.conveyor.dto.LoanOfferDTO;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class OfferService {

    private LoanApplicationRequestDTO loanApplicationRequestDTO;

    private static long id = 1;

    private final BigDecimal rate;

    private LoanOfferDTO getLoanOfferDTOWithIDRequestAmountTermRate() {
        LoanOfferDTO loanOfferDTO = new LoanOfferDTO();
        loanOfferDTO.setApplicationId(id++);
        loanOfferDTO.setRequestedAmount(loanApplicationRequestDTO.getAmount());
        loanOfferDTO.setTerm(loanApplicationRequestDTO.getTerm());
        loanOfferDTO.setRate(this.rate);

        return loanOfferDTO;
    }

    private void assignTotalAmount(LoanOfferDTO loanOfferDTO) {
        if (loanOfferDTO.getInsuranceEnabled()) {
            loanOfferDTO.setTotalAmount(loanOfferDTO.getRequestedAmount().multiply(new BigDecimal("1.1")));
        } else {
            loanOfferDTO.setTotalAmount(loanOfferDTO.getRequestedAmount());
        }
    }

    private void assignRate(LoanOfferDTO loanOfferDTO) {
        if (loanOfferDTO.getInsuranceEnabled()) {
            loanOfferDTO.setRate(new BigDecimal("1.1"));
        }
        if (loanOfferDTO.getSalaryClient()) {
            loanOfferDTO.setRate(loanOfferDTO.getRate().subtract(new BigDecimal("0.05")));
        }

    }

    private void assignMonthlyPayment(LoanOfferDTO loanOfferDTO) {
        BigDecimal totalAmount = loanOfferDTO.getTotalAmount();
        BigDecimal rate = loanOfferDTO.getRate();
        Integer term = loanOfferDTO.getTerm();
        BigDecimal clearPayment = totalAmount.divide(BigDecimal.valueOf(term), RoundingMode.HALF_UP);
        BigDecimal monthlyPayment = totalAmount.multiply(rate).subtract(totalAmount).divide(new BigDecimal("12"), RoundingMode.HALF_UP).add(clearPayment);
        loanOfferDTO.setMonthlyPayment(monthlyPayment);
    }


    public List<LoanOfferDTO> getOffers() {
        List<LoanOfferDTO> result = new ArrayList<>(4);

        LoanOfferDTO loanOfferDTOTrueTrue = getLoanOfferDTOWithIDRequestAmountTermRate();
        loanOfferDTOTrueTrue.setInsuranceEnabled(true);
        loanOfferDTOTrueTrue.setSalaryClient(true);
        assignTotalAmount(loanOfferDTOTrueTrue);
        assignRate(loanOfferDTOTrueTrue);
        assignMonthlyPayment(loanOfferDTOTrueTrue);

        result.add(loanOfferDTOTrueTrue);


        LoanOfferDTO loanOfferDTOFalseFalse = getLoanOfferDTOWithIDRequestAmountTermRate();
        loanOfferDTOFalseFalse.setInsuranceEnabled(false);
        loanOfferDTOFalseFalse.setSalaryClient(false);
        assignTotalAmount(loanOfferDTOFalseFalse);
        assignRate(loanOfferDTOFalseFalse);
        assignMonthlyPayment(loanOfferDTOFalseFalse);

        result.add(loanOfferDTOFalseFalse);

        LoanOfferDTO loanOfferDTOTrueFalse = getLoanOfferDTOWithIDRequestAmountTermRate();
        loanOfferDTOTrueFalse.setInsuranceEnabled(true);
        loanOfferDTOTrueFalse.setSalaryClient(false);
        assignTotalAmount(loanOfferDTOTrueFalse);
        assignRate(loanOfferDTOTrueFalse);
        assignMonthlyPayment(loanOfferDTOTrueFalse);

        result.add(loanOfferDTOTrueFalse);

        LoanOfferDTO loanOfferDTOFalseTrue = getLoanOfferDTOWithIDRequestAmountTermRate();
        loanOfferDTOFalseTrue.setInsuranceEnabled(false);
        loanOfferDTOFalseTrue.setSalaryClient(true);
        assignTotalAmount(loanOfferDTOFalseTrue);
        assignRate(loanOfferDTOFalseTrue);
        assignMonthlyPayment(loanOfferDTOFalseTrue);
        result.add(loanOfferDTOFalseTrue);
        Collections.sort(result, new RateComparator());
        return result;
    }


    public void setLoanApplicationRequestDTO(LoanApplicationRequestDTO loanApplicationRequestDTO) {
        this.loanApplicationRequestDTO = loanApplicationRequestDTO;
    }

    public OfferService(@Value("${rate}") BigDecimal rate) {
        this.rate = rate;
    }
}
