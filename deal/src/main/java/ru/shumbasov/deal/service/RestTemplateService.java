package ru.shumbasov.deal.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import ru.shumbasov.deal.dto.CreditDTO;
import ru.shumbasov.deal.dto.LoanApplicationRequestDTO;
import ru.shumbasov.deal.dto.LoanOfferDTO;
import ru.shumbasov.deal.dto.ScoringDataDTO;

import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.List;

@Service
@Slf4j
public class RestTemplateService {

    private final RestTemplate restTemplate;
    private final String conveyorOfferOffersUrl;
    private final String conveyorCalculationUrl;

    public RestTemplateService(RestTemplate restTemplate,
                               @Value("${offers}") String conveyorOfferOffersUrl,
                               @Value("${calculation}") String conveyorCalculationUrl) {
        this.restTemplate = restTemplate;
        this.conveyorOfferOffersUrl = conveyorOfferOffersUrl;
        this.conveyorCalculationUrl = conveyorCalculationUrl;
    }

    public List<LoanOfferDTO> getListFromConveyorOffers(LoanApplicationRequestDTO loanApplicationRequestDTO) throws URISyntaxException {
        RequestEntity<LoanApplicationRequestDTO> requestEntity = RequestEntity.post(new URI(conveyorOfferOffersUrl)).
                accept(MediaType.APPLICATION_JSON).
                contentType(MediaType.APPLICATION_JSON).
                body(loanApplicationRequestDTO);

        ResponseEntity<List<LoanOfferDTO>> responseEntity = restTemplate.exchange(conveyorOfferOffersUrl,
                HttpMethod.POST,
                requestEntity,
                new ParameterizedTypeReference<List<LoanOfferDTO>>() {
                });
        log.info("Получились List<LoanOfferDTO> из conveyor/offers");
        return responseEntity.getBody();
    }

    public CreditDTO getCreditFromConveyorCalculation(ScoringDataDTO scoringDataDTO) throws URISyntaxException {
        RequestEntity<ScoringDataDTO> requestEntity = RequestEntity.post(new URI(conveyorCalculationUrl)).
                accept(MediaType.APPLICATION_JSON).
                contentType(MediaType.APPLICATION_JSON).
                body(scoringDataDTO);

        ResponseEntity<CreditDTO> responseEntity = restTemplate.exchange(conveyorCalculationUrl,
                HttpMethod.POST,
                requestEntity,
                CreditDTO.class);
        log.info("Получили CreditDTO из conveyor/calculation");
        return responseEntity.getBody();

    }
}
