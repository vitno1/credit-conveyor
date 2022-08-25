package ru.shum.application.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.shum.application.dto.LoanApplicationRequestDTO;
import ru.shum.application.dto.LoanOfferDTO;

import java.net.URISyntaxException;
import java.util.List;

@Service
@Slf4j
public class ApplicationService {
    private LoanApplicationRequestDTO loanApplicationRequestDTO;
    private final RestTemplateService restTemplateService;

    public List<LoanOfferDTO> getOffers()  {
        List<LoanOfferDTO> offersList = null;
        try {
            offersList = restTemplateService.getListFromConveyorOffers(loanApplicationRequestDTO);
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        log.info("Метод getOffers - Получаем List<LoanOfferDTO> из deal/application");
        return offersList;
    }

    public ApplicationService(RestTemplateService restTemplateService) {
        this.restTemplateService = restTemplateService;
    }

    public void setLoanApplicationRequestDTO(LoanApplicationRequestDTO loanApplicationRequestDTO) {
        this.loanApplicationRequestDTO = loanApplicationRequestDTO;
    }
}
