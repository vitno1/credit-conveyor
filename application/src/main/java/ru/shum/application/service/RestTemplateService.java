package ru.shum.application.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import ru.shum.application.dto.LoanApplicationRequestDTO;
import ru.shum.application.dto.LoanOfferDTO;


import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

@Service
@Slf4j
public class RestTemplateService {

    private final RestTemplate restTemplate;
    private final String dealApplicationUrl;
    private final String dealOfferUrl;


    public RestTemplateService(RestTemplate restTemplate, @Value("${application}") String dealApplicationUrl, @Value("${offer}") String dealOfferUrl) {
        this.restTemplate = restTemplate;
        this.dealApplicationUrl = dealApplicationUrl;
        this.dealOfferUrl = dealOfferUrl;
    }

    public List<LoanOfferDTO> getListFromConveyorOffers(LoanApplicationRequestDTO loanApplicationRequestDTO) throws URISyntaxException {
        RequestEntity<LoanApplicationRequestDTO> requestEntity = RequestEntity.post(new URI(dealApplicationUrl)).
                accept(MediaType.APPLICATION_JSON).
                contentType(MediaType.APPLICATION_JSON).
                body(loanApplicationRequestDTO);

        ResponseEntity<List<LoanOfferDTO>> responseEntity = restTemplate.exchange(dealApplicationUrl,
                HttpMethod.POST,
                requestEntity,
                new ParameterizedTypeReference<List<LoanOfferDTO>>() {
                });
        log.info("Метод getListFromConveyorOffers - Получили List<LoanOfferDTO> из deal/application");
        return responseEntity.getBody();
    }

    public void postToDealOffer(LoanOfferDTO loanOfferDTO) throws URISyntaxException {
        RequestEntity<LoanOfferDTO> requestEntity = RequestEntity.post(new URI(dealOfferUrl)).
                accept(MediaType.APPLICATION_JSON).
                contentType(MediaType.APPLICATION_JSON).
                body(loanOfferDTO);
        log.info("Метод postToDealOffer - отпарвляем запрос на deal/offer");
        restTemplate.put(dealOfferUrl , requestEntity);
    }

}
