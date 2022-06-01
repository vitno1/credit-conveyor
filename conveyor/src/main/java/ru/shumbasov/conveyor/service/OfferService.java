package ru.shumbasov.conveyor.service;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import ru.shumbasov.conveyor.dto.LoanApplicationRequestDTO;
import ru.shumbasov.conveyor.dto.LoanOfferDTO;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Service
public class OfferService {

    private LoanApplicationRequestDTO loanApplicationRequestDTO;

    private static long id = 1;

    private final BigDecimal rate;

    public OfferService(@Value("${rate}") BigDecimal rate) {
        this.rate = rate;
    }


    private LoanOfferDTO getLoanOfferDTOWithIDRequestAmountTerm() {
        LoanOfferDTO loanOfferDTO = new LoanOfferDTO();
        loanOfferDTO.setApplicationId(id++);
        loanOfferDTO.setRequestedAmount(loanApplicationRequestDTO.getAmount());
        loanOfferDTO.setTerm(loanApplicationRequestDTO.getTerm());
        System.out.println("1");
        return loanOfferDTO;
    }

    private void assignTotalAmount(LoanOfferDTO loanOfferDTO) {
        if (loanOfferDTO.getInsuranceEnabled()) {
            loanOfferDTO.setTotalAmount(loanOfferDTO.getRequestedAmount().multiply(new BigDecimal("1.1")));
            System.out.println("2");
        } else {
            loanOfferDTO.setTotalAmount(loanOfferDTO.getRequestedAmount());
            System.out.println("2");
        }
    }

    private void assignRate(LoanOfferDTO loanOfferDTO) {
        System.out.println("3");
        System.out.println(this.rate);
    }


    public List<LoanOfferDTO> getOffers() {
        List<LoanOfferDTO> result = new ArrayList<>(4);

        LoanOfferDTO loanOfferDTOTrueTrue = getLoanOfferDTOWithIDRequestAmountTerm();
        loanOfferDTOTrueTrue.setInsuranceEnabled(true);
        loanOfferDTOTrueTrue.setSalaryClient(true);
        assignTotalAmount(loanOfferDTOTrueTrue);
        assignRate(loanOfferDTOTrueTrue);

        result.add(loanOfferDTOTrueTrue);


        LoanOfferDTO loanOfferDTOFalseFalse = getLoanOfferDTOWithIDRequestAmountTerm();
        loanOfferDTOFalseFalse.setInsuranceEnabled(false);
        loanOfferDTOFalseFalse.setSalaryClient(false);
        result.add(loanOfferDTOFalseFalse);

        LoanOfferDTO loanOfferDTOTrueFalse = getLoanOfferDTOWithIDRequestAmountTerm();
        loanOfferDTOFalseFalse.setInsuranceEnabled(true);
        loanOfferDTOFalseFalse.setSalaryClient(false);
        result.add(loanOfferDTOTrueFalse);

        LoanOfferDTO loanOfferDTOFalseTrue = getLoanOfferDTOWithIDRequestAmountTerm();
        loanOfferDTOFalseTrue.setInsuranceEnabled(false);
        loanOfferDTOFalseTrue.setSalaryClient(true);
        result.add(loanOfferDTOFalseTrue);


        return result;
    }


    public void setLoanApplicationRequestDTO(LoanApplicationRequestDTO loanApplicationRequestDTO) {
        this.loanApplicationRequestDTO = loanApplicationRequestDTO;
    }
}
