package ru.shumbasov.deal.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.shumbasov.deal.dto.LoanOfferDTO;
import ru.shumbasov.deal.entity.*;
import ru.shumbasov.deal.enums.ApplicationStatus;
import ru.shumbasov.deal.repository.ApplicationRepository;
import ru.shumbasov.deal.repository.ApplicationStatusHistoryDTOEntityRepository;
import ru.shumbasov.deal.repository.LoanOfferDTOEntityRepository;

import java.time.LocalDate;
import java.util.Optional;

@Service
@Slf4j
public class ApplicationEntityService {
    private final ApplicationRepository applicationRepository;
    private final ApplicationStatusHistoryDTOEntityRepository applicationStatusHistoryDTOEntityRepository;
    private final LoanOfferDTOEntityRepository loanOfferDTOEntityRepository;

    Application createApplication(Client client) {
        Application application = new Application();
        application.setClient(client);
        application.setApplicationStatus(ApplicationStatus.PREAPPROVAL);
        application.setCreationDate(LocalDate.now());
        log.info("Application успешно создан,Client успешно создан,установлен статус Preaproval, добавлена дата создания заявки");
        return applicationRepository.save(application);
    }

    void setCredit(Application application, Credit credit) {
        application.setCredit(credit);
        applicationRepository.save(application);
    }

    Application findById(Long id) {
        Application application = null;
        final Optional<Application> optional = applicationRepository.findById(id);
        if (optional.isPresent()) {
            application = optional.get();
        } else {
            log.error(String.valueOf(new IllegalArgumentException("Application dont exist")));
        }
        return application;
    }

    Application setStatus(Application application, ApplicationStatus applicationStatus) {
        ApplicationStatusHistoryDTOEntity applicationStatusHistoryDTOEntity = new ApplicationStatusHistoryDTOEntity();
        applicationStatusHistoryDTOEntity.setStatus(application.getApplicationStatus());
        applicationStatusHistoryDTOEntity.setTime(LocalDate.now());
        applicationStatusHistoryDTOEntity.setChangeType(applicationStatus);
        applicationStatusHistoryDTOEntityRepository.save(applicationStatusHistoryDTOEntity);
        application.getStatusHistory().add(applicationStatusHistoryDTOEntity);
        application.setApplicationStatus(applicationStatus);
        return applicationRepository.save(application);
    }

    Application setAppliedOffer(Application application, LoanOfferDTO loanOfferDTO) {
        LoanOfferDTOEntity loanOfferDTOEntity = new LoanOfferDTOEntity();
        loanOfferDTOEntity.setRequestedAmount(loanOfferDTO.getRequestedAmount());
        loanOfferDTOEntity.setTotalAmount(loanOfferDTO.getTotalAmount());
        loanOfferDTOEntity.setTerm(loanOfferDTO.getTerm());
        loanOfferDTOEntity.setMonthlyPayment(loanOfferDTO.getMonthlyPayment());
        loanOfferDTOEntity.setRate(loanOfferDTO.getRate());
        loanOfferDTOEntity.setIsInsuranceEnabled(loanOfferDTO.getInsuranceEnabled());
        loanOfferDTOEntity.setIsSalaryClient(loanOfferDTO.getSalaryClient());
        loanOfferDTOEntityRepository.save(loanOfferDTOEntity);
        application.setAppliedOffer(loanOfferDTOEntity);
        return applicationRepository.save(application);
    }


    public ApplicationEntityService(ApplicationRepository applicationRepository,
                                    ApplicationStatusHistoryDTOEntityRepository applicationStatusHistoryDTOEntityRepository,
                                    LoanOfferDTOEntityRepository loanOfferDTOEntityRepository) {
        this.applicationRepository = applicationRepository;
        this.applicationStatusHistoryDTOEntityRepository = applicationStatusHistoryDTOEntityRepository;
        this.loanOfferDTOEntityRepository = loanOfferDTOEntityRepository;
    }
}
