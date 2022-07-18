package ru.shumbasov.deal.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.shumbasov.deal.dto.CreditDTO;
import ru.shumbasov.deal.dto.FinishRegistrationRequestDTO;
import ru.shumbasov.deal.dto.ScoringDataDTO;
import ru.shumbasov.deal.entity.Application;
import ru.shumbasov.deal.enums.ApplicationStatus;

import java.net.URISyntaxException;

@Service
@Slf4j
public class CalculationService {
    private FinishRegistrationRequestDTO finishRegistrationRequestDTO;
    private final ApplicationEntityService applicationEntityService;
    private final RestTemplateService restTemplateService;

    public void calculateAndFinishRegistration(Long applicationId) {
        Application application = applicationEntityService.findById(applicationId);
        ScoringDataDTO scoringDataDTO = new ScoringDataDTO();
        scoringDataDTO.setGender(finishRegistrationRequestDTO.getGender());
        scoringDataDTO.setMaritalStatus(finishRegistrationRequestDTO.getMaritalStatus());
        scoringDataDTO.setDependentAmount(finishRegistrationRequestDTO.getDependentAmount());
        scoringDataDTO.setPassportIssueDate(finishRegistrationRequestDTO.getPassportIssueDate());
        scoringDataDTO.setPassportIssueBranch(finishRegistrationRequestDTO.getPassportIssueBranch());
        scoringDataDTO.setEmployment(finishRegistrationRequestDTO.getEmployment());
        scoringDataDTO.setAccount(finishRegistrationRequestDTO.getAccount());
        scoringDataDTO.setLastName(application.getClient().getLastName());
        scoringDataDTO.setFirstName(application.getClient().getFirstName());
        scoringDataDTO.setMiddleName(application.getClient().getMiddleName());
        scoringDataDTO.setBirthdate(application.getClient().getBirthDate());
        scoringDataDTO.setPassportSeries(application.getClient().getPassport().getSeries());
        scoringDataDTO.setPassportNumber(application.getClient().getPassport().getNumber());
        scoringDataDTO.setAmount(application.getAppliedOffer().getRequestedAmount());
        scoringDataDTO.setTerm(application.getAppliedOffer().getTerm());
        scoringDataDTO.setIsInsuranceEnabled(application.getAppliedOffer().getIsInsuranceEnabled());
        scoringDataDTO.setIsSalaryClient(application.getAppliedOffer().getIsSalaryClient());
        log.info("Насыщаем ScoringDataDTO данными из FinishRegistrationRequestDTO и сущностей Clien and AppliedOffer");
        try {
            final CreditDTO creditDTO = restTemplateService.getCreditFromConveyorCalculation(scoringDataDTO);
            applicationEntityService.setFinishData(application, finishRegistrationRequestDTO, creditDTO);
            applicationEntityService.setStatus(application, ApplicationStatus.CC_APPROVED);
        } catch (URISyntaxException e) {
            log.error(e.getMessage());
        }

    }

    public CalculationService(ApplicationEntityService applicationEntityService, RestTemplateService restTemplateService) {
        this.applicationEntityService = applicationEntityService;
        this.restTemplateService = restTemplateService;
    }

    public void setFinishRegistrationRequestDTO(FinishRegistrationRequestDTO finishRegistrationRequestDTO) {
        this.finishRegistrationRequestDTO = finishRegistrationRequestDTO;
    }
}
