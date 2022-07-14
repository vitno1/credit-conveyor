package ru.shumbasov.deal.service;

import org.springframework.stereotype.Service;
import ru.shumbasov.deal.dto.LoanApplicationRequestDTO;
import ru.shumbasov.deal.entity.Credit;
import ru.shumbasov.deal.repository.CreditRepository;

@Service
public class CreditEntityService {
    private final CreditRepository creditRepository;

    Credit createCredit(LoanApplicationRequestDTO loanApplicationRequestDTO){
        Credit credit = new Credit();
        credit.setAmount(loanApplicationRequestDTO.getAmount());
        credit.setTerm(loanApplicationRequestDTO.getTerm());
        return creditRepository.save(credit);

    }


    public CreditEntityService(CreditRepository creditRepository) {
        this.creditRepository = creditRepository;
    }
}
