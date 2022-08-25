package ru.shumbasov.deal.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.shumbasov.deal.dto.LoanApplicationRequestDTO;
import ru.shumbasov.deal.dto.LoanOfferDTO;
import ru.shumbasov.deal.entity.Application;
import ru.shumbasov.deal.entity.Client;
import ru.shumbasov.deal.entity.Credit;
import ru.shumbasov.deal.service.*;

import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class ApplicationServiceTest {

    @Mock
    private ApplicationEntityService applicationEntityService;
    @Mock
    private  ClientEntityService clientEntityService;
    @Mock
    private  CreditEntityService creditEntityService;
    @Mock
    private  RestTemplateService restTemplateService;

    @InjectMocks
    ApplicationService applicationService;

    @Test
    void getOffers() throws URISyntaxException {
        List<LoanOfferDTO> list = new ArrayList<>();
        for(int i = 0;i < 4;i++){
            list.add(new LoanOfferDTO());
        }

        Mockito.when(applicationEntityService.createApplication(new Client())).thenReturn(new Application());
        Mockito.when(clientEntityService.createClient(ArgumentMatchers.any())).thenReturn(new Client());
        Mockito.when(restTemplateService.getListFromConveyorOffers(ArgumentMatchers.any())).thenReturn(list);
        Mockito.when(creditEntityService.createCredit(ArgumentMatchers.any())).thenReturn(new Credit());
        applicationService.getOffers();
    }
    @Test
    void getOffersWithInvalidArguments() throws URISyntaxException {
        Mockito.when(restTemplateService.getListFromConveyorOffers(ArgumentMatchers.any())).thenReturn(null);
        List<LoanOfferDTO> listReturnedByService = applicationService.getOffers();
       assertThat(listReturnedByService).isNull();
    }
}
