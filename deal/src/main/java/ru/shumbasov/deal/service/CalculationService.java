package ru.shumbasov.deal.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.shumbasov.deal.dto.FinishRegistrationRequestDTO;
import ru.shumbasov.deal.dto.ScoringDataDTO;
import ru.shumbasov.deal.entity.Application;

@Service
@Slf4j
public class CalculationService {
    private FinishRegistrationRequestDTO finishRegistrationRequestDTO;
    private final ApplicationEntityService applicationEntityService;

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
        System.out.println(scoringDataDTO);

    }

    public CalculationService(ApplicationEntityService applicationEntityService) {
        this.applicationEntityService = applicationEntityService;
    }

    public void setFinishRegistrationRequestDTO(FinishRegistrationRequestDTO finishRegistrationRequestDTO) {
        this.finishRegistrationRequestDTO = finishRegistrationRequestDTO;
    }
}
