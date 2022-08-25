package ru.shum.application.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.shum.application.dto.LoanOfferDTO;

import java.net.URISyntaxException;

@ExtendWith(MockitoExtension.class)
public class OfferServiceTest {
    @InjectMocks
    private OfferService offerService;
    @Mock
    RestTemplateService restTemplateService;

    @Test
    void test() throws URISyntaxException {
        LoanOfferDTO loanOfferDTO = new LoanOfferDTO();
       offerService.setLoanOfferDTO(loanOfferDTO);
    }
}
