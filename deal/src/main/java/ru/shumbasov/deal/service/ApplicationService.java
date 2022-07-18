package ru.shumbasov.deal.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.shumbasov.deal.dto.LoanApplicationRequestDTO;
import ru.shumbasov.deal.dto.LoanOfferDTO;
import ru.shumbasov.deal.entity.Application;
import ru.shumbasov.deal.entity.Client;
import ru.shumbasov.deal.entity.Credit;

import java.net.URISyntaxException;
import java.util.List;

@Service
@Slf4j
public class ApplicationService {
    private LoanApplicationRequestDTO loanApplicationRequestDTO;
    private final ApplicationEntityService applicationEntityService;
    private final ClientEntityService clientEntityService;
    private final CreditEntityService creditEntityService;
    private final RestTemplateService restTemplateService;

    public ApplicationService(ApplicationEntityService applicationEntityService,
                              ClientEntityService clientEntityService,
                              CreditEntityService creditEntityService,
                              RestTemplateService restTemplateService) {
        this.applicationEntityService = applicationEntityService;
        this.clientEntityService = clientEntityService;
        this.creditEntityService = creditEntityService;
        this.restTemplateService = restTemplateService;
    }

    public List<LoanOfferDTO> getOffers() {
        final Client client = clientEntityService.createClient(loanApplicationRequestDTO);
        final Application application = applicationEntityService.createApplication(client);
        final Credit credit = creditEntityService.createCredit(loanApplicationRequestDTO);
        applicationEntityService.setCredit(application, credit);
        try {
            List<LoanOfferDTO> offersList = restTemplateService.getListFromConveyorOffers(loanApplicationRequestDTO);
            for (LoanOfferDTO loanOfferDTO : offersList) {
                loanOfferDTO.setApplicationId(application.getId());
            }
            return offersList;
        } catch (URISyntaxException  | NullPointerException e) {
            log.error(e.getMessage());
        }
        return null;
    }


    public void setLoanApplicationRequestDTO(LoanApplicationRequestDTO loanApplicationRequestDTO) {
        this.loanApplicationRequestDTO = loanApplicationRequestDTO;
    }


}
