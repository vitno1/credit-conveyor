package ru.shum.application.service;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.shum.application.dto.LoanApplicationRequestDTO;
import ru.shum.application.dto.LoanOfferDTO;

import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

@ExtendWith(MockitoExtension.class)
public class ApplicationServiceTest {
    @InjectMocks
    private ApplicationService applicationService;
    @Mock
    private RestTemplateService restTemplateService;

    @Test
    void test() throws URISyntaxException {
        List<LoanOfferDTO> list = new ArrayList<>();
        for(int i = 0;i<4;i++){
            list.add(new LoanOfferDTO());
        }
        Mockito.when(restTemplateService.getListFromConveyorOffers(ArgumentMatchers.any())).thenReturn(list);
        applicationService.setLoanApplicationRequestDTO(new LoanApplicationRequestDTO());
        final List<LoanOfferDTO> offers = applicationService.getOffers();
        Assertions.assertThat(offers.size()).isEqualTo(4);
    }
}
