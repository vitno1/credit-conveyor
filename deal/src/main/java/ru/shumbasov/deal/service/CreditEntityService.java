package ru.shumbasov.deal.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.shumbasov.deal.dto.LoanApplicationRequestDTO;
import ru.shumbasov.deal.entity.Credit;
import ru.shumbasov.deal.repository.CreditRepository;

@Service
@Slf4j
public class CreditEntityService {
    private final CreditRepository creditRepository;

    Credit createCredit(LoanApplicationRequestDTO loanApplicationRequestDTO){
        Credit credit = new Credit();
        credit.setAmount(loanApplicationRequestDTO.getAmount());
        credit.setTerm(loanApplicationRequestDTO.getTerm());
        log.info("Создали сущность Credit , засетили Term and Amount из LoanApplicationRequestDTO");
        return creditRepository.save(credit);

    }


    public CreditEntityService(CreditRepository creditRepository) {
        this.creditRepository = creditRepository;
    }
}
