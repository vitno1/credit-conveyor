package ru.shumbasov.deal.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.shumbasov.deal.dto.LoanApplicationRequestDTO;
import ru.shumbasov.deal.entity.Credit;
import ru.shumbasov.deal.repository.CreditRepository;

@ExtendWith(MockitoExtension.class)
public class CreditEntityServiceTest {
    @InjectMocks
    private CreditEntityService creditEntityService;
    @Mock
    private CreditRepository creditRepository;

    @Test
    void createCredit(){
        Credit credit = new Credit();
        Mockito.when(creditRepository.save(credit)).thenReturn(credit);
        creditEntityService.createCredit(new LoanApplicationRequestDTO());
    }
}
