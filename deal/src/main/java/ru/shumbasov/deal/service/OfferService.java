package ru.shumbasov.deal.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.shumbasov.deal.dto.LoanOfferDTO;
import ru.shumbasov.deal.entity.Application;
import ru.shumbasov.deal.entity.ApplicationStatusHistoryDTOEntity;
import ru.shumbasov.deal.enums.ApplicationStatus;

@Service
@Slf4j
public class OfferService {
    private LoanOfferDTO loanOfferDTO;
    private final ApplicationEntityService applicationEntityService;
    private final ApplicationStatusEntityService applicationStatusEntityService;

    public OfferService(ApplicationEntityService applicationEntityService,
                        ApplicationStatusEntityService applicationStatusEntityService) {
        this.applicationEntityService = applicationEntityService;
        this.applicationStatusEntityService = applicationStatusEntityService;
    }

    public void saveOffer() {
        Application application = applicationEntityService.findById(loanOfferDTO.getApplicationId());
        applicationEntityService.setStatus(application, ApplicationStatus.APPROVED);
        applicationEntityService.setAppliedOffer(application ,loanOfferDTO);
    }

    public void setLoanOfferDTO(LoanOfferDTO loanOfferDTO) {
        this.loanOfferDTO = loanOfferDTO;
    }
}
