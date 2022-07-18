package ru.shumbasov.deal.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.shumbasov.deal.dto.CreditDTO;
import ru.shumbasov.deal.dto.FinishRegistrationRequestDTO;
import ru.shumbasov.deal.dto.ScoringDataDTO;
import ru.shumbasov.deal.entity.Application;
import ru.shumbasov.deal.entity.Client;
import ru.shumbasov.deal.entity.LoanOfferDTOEntity;
import ru.shumbasov.deal.entity.Passport;

import java.net.URISyntaxException;

@ExtendWith(MockitoExtension.class)
public class CalculationServiceTest {
    @InjectMocks
    private CalculationService calculationService;
    @Mock
    private ApplicationEntityService applicationEntityService;
    @Mock
    private RestTemplateService restTemplateService;

    @Test
    void calculateAndFinishRegistration() throws URISyntaxException {
        Application application = new Application();
        Client client = new Client();
        Passport passport = new Passport();
        application.setClient(client);
        application.getClient().setPassport(passport);
        LoanOfferDTOEntity loanOfferDTOEntity = new LoanOfferDTOEntity();
        application.setAppliedOffer(loanOfferDTOEntity);
        calculationService.setFinishRegistrationRequestDTO(new FinishRegistrationRequestDTO());
        Mockito.when(applicationEntityService.findById(ArgumentMatchers.any())).thenReturn(application);
        Mockito.when(restTemplateService.getCreditFromConveyorCalculation(ArgumentMatchers.any())).thenReturn(new CreditDTO());
        calculationService.calculateAndFinishRegistration(1L);
    }
}
