package ru.shum.application.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.shum.application.dto.LoanOfferDTO;

import java.net.URISyntaxException;

@Service
@Slf4j
public class OfferService {
    private LoanOfferDTO loanOfferDTO;
    private final RestTemplateService restTemplateService;

    public void pickOffer(){
        try {
            restTemplateService.postToDealOffer(loanOfferDTO);
            log.info("Отправляем запрос на api deal/offer");
        } catch (URISyntaxException e) {
           log.error(String.valueOf(e));
        }
    }

    public void setLoanOfferDTO(LoanOfferDTO loanOfferDTO) {
        this.loanOfferDTO = loanOfferDTO;
    }

    public OfferService(RestTemplateService restTemplateService) {
        this.restTemplateService = restTemplateService;
    }
}
