package ru.shumbasov.conveyor.service;

import org.springframework.stereotype.Service;
import ru.shumbasov.conveyor.dto.LoanApplicationRequestDTO;
import ru.shumbasov.conveyor.dto.LoanOfferDTO;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Service
public class OfferService {
    //    private Long applicationId; //id кредита
//    private BigDecimal requestedAmount; //запрашиваемая сумма
//    private BigDecimal totalAmount; // общая сумма *
//    private Integer term; //срок *
//    private BigDecimal monthlyPayment; //ежемесячный платеж *
//    private BigDecimal rate; //ставка * базовая ставка хардкодится из пропертей
//    private Boolean isInsuranceEnabled; //включена ли страховка
//    private Boolean isSalaryClient; //зп клиента норм?

    private LoanApplicationRequestDTO loanApplicationRequestDTO;

    public void setLoanApplicationRequestDTO(LoanApplicationRequestDTO loanApplicationRequestDTO) {
        this.loanApplicationRequestDTO = loanApplicationRequestDTO;
    }

    private static long id = 1;

    private void assignTotalAmount(){
                BigDecimal totalAmount;
        BigDecimal amount = loanApplicationRequestDTO.getAmount();
        BigDecimal percent = new BigDecimal("1.1");
        totalAmount = amount.multiply(percent);
    }


    private LoanOfferDTO getLoanOfferDTOWithIDRequestAmountTerm(){
        LoanOfferDTO loanOfferDTO = new LoanOfferDTO();
        loanOfferDTO.setApplicationId(id++);
        loanOfferDTO.setRequestedAmount(loanApplicationRequestDTO.getAmount());
        loanOfferDTO.setTerm(loanApplicationRequestDTO.getTerm());
        return loanOfferDTO;
    }




    public List<LoanOfferDTO> getOffers() {
        List<LoanOfferDTO> result = new ArrayList<>(4);
        LoanOfferDTO loanOfferDTO = getLoanOfferDTOWithIDRequestAmountTerm();


        return null;
    }

}
