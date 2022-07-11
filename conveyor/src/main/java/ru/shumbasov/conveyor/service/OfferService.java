package ru.shumbasov.conveyor.service;

import lombok.extern.slf4j.Slf4j;
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
@Slf4j
public class OfferService {

    private LoanApplicationRequestDTO loanApplicationRequestDTO;

    private static long id = 1;

    private final BigDecimal rate;

    private LoanOfferDTO getLoanOfferDTO(Boolean isInsuranceEnabled, Boolean isSalaryClient) {
        log.info("Start creating LoanOfferDTO");
        LoanOfferDTO loanOfferDTO = new LoanOfferDTO();
        loanOfferDTO.setApplicationId(id++);
        loanOfferDTO.setRequestedAmount(loanApplicationRequestDTO.getAmount());
        loanOfferDTO.setTerm(loanApplicationRequestDTO.getTerm());
        loanOfferDTO.setRate(this.rate);
        loanOfferDTO.setInsuranceEnabled(isInsuranceEnabled);
        loanOfferDTO.setSalaryClient(isSalaryClient);
        assignTotalAmount(loanOfferDTO);
        assignRate(loanOfferDTO);
        assignMonthlyPayment(loanOfferDTO);

        return loanOfferDTO;
    }

    private void assignTotalAmount(LoanOfferDTO loanOfferDTO) {
        log.info("Set TotalAmount");
        if (loanOfferDTO.getInsuranceEnabled()) {
            loanOfferDTO.setTotalAmount(loanOfferDTO.getRequestedAmount().multiply(new BigDecimal("1.1")));
        } else {
            loanOfferDTO.setTotalAmount(loanOfferDTO.getRequestedAmount());
        }
    }

    private void assignRate(LoanOfferDTO loanOfferDTO) {
        log.info("Set Rate");
        if (loanOfferDTO.getInsuranceEnabled()) {
            loanOfferDTO.setRate(new BigDecimal("1.1"));
        }
        if (loanOfferDTO.getSalaryClient()) {
            loanOfferDTO.setRate(loanOfferDTO.getRate().subtract(new BigDecimal("0.05")));
        }
    }

    private void assignMonthlyPayment(LoanOfferDTO loanOfferDTO) {
        log.info("Calculate MonthlyPayment");
        BigDecimal totalAmount = loanOfferDTO.getTotalAmount();
        BigDecimal rate = loanOfferDTO.getRate();
        Integer term = loanOfferDTO.getTerm();
        BigDecimal clearPayment = totalAmount.divide(BigDecimal.valueOf(term), RoundingMode.HALF_UP);
        BigDecimal monthlyPayment = totalAmount.multiply(rate).subtract(totalAmount).divide(new BigDecimal("12"), RoundingMode.HALF_UP).add(clearPayment);
        loanOfferDTO.setMonthlyPayment(monthlyPayment);
    }


    public List<LoanOfferDTO> getOffers() {
        List<LoanOfferDTO> loanOfferDTO = List.of(getLoanOfferDTO(true, true),
                getLoanOfferDTO(false, false),
                getLoanOfferDTO(true, false),
                getLoanOfferDTO(false, true));
        ArrayList<LoanOfferDTO> result = new ArrayList<>(loanOfferDTO);
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
