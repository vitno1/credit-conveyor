package ru.shumbasov.deal.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.shumbasov.deal.dto.LoanOfferDTO;
import ru.shumbasov.deal.entity.Application;

@ExtendWith(MockitoExtension.class)
public class OfferServiceTest {
    @InjectMocks
    private OfferService offerService;
    @Mock
    private ApplicationEntityService applicationEntityService;

    @Test
    void saveOffer() {
        Mockito.when(applicationEntityService.findById(ArgumentMatchers.any())).thenReturn(new Application());
        offerService.setLoanOfferDTO(new LoanOfferDTO());
        offerService.saveOffer();
    }
}
