package ru.shumbasov.deal.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.shumbasov.deal.dto.CreditDTO;
import ru.shumbasov.deal.dto.FinishRegistrationRequestDTO;
import ru.shumbasov.deal.dto.LoanOfferDTO;
import ru.shumbasov.deal.dto.PaymentScheduleElement;
import ru.shumbasov.deal.entity.*;
import ru.shumbasov.deal.enums.ApplicationStatus;
import ru.shumbasov.deal.repository.ApplicationRepository;
import ru.shumbasov.deal.repository.ApplicationStatusHistoryDTOEntityRepository;
import ru.shumbasov.deal.repository.LoanOfferDTOEntityRepository;
import ru.shumbasov.deal.repository.PaymentScheduleElementDTOEntityRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class ApplicationEntityService {
    private final ApplicationRepository applicationRepository;
    private final ApplicationStatusHistoryDTOEntityRepository applicationStatusHistoryDTOEntityRepository;
    private final LoanOfferDTOEntityRepository loanOfferDTOEntityRepository;
    private final PaymentScheduleElementDTOEntityRepository paymentScheduleElementDTOEntityRepository;

    Application createApplication(Client client) {
        Application application = new Application();
        application.setClient(client);
        application.setApplicationStatus(ApplicationStatus.PREAPPROVAL);
        application.setCreationDate(LocalDate.now());
        log.info("Создали сущеность Application , засетили раннее созданную сущность Client , засетили applicationStatus и CreationDate");
        return applicationRepository.save(application);
    }

    void setCredit(Application application, Credit credit) {
        application.setCredit(credit);
        applicationRepository.save(application);
        log.info("Засетили сущность Credit в сущность Application");
    }

    Application findById(Long id) {
        Application application = null;
        final Optional<Application> optional = applicationRepository.findById(id);
        if (optional.isPresent()) {
            application = optional.get();
        } else {
            log.error(String.valueOf(new IllegalArgumentException("Application dont exist")));
        }
        log.info("Находим сущность Application по id");
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
        log.info("Обновляем applicationStatus у сущности Application , а также добавляем ApplicationStatusHistoryDTOEntity");
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
        log.info("Засетили сущность appliedOffer");
        return applicationRepository.save(application);
    }

    Application setFinishData(Application application, FinishRegistrationRequestDTO finishRegistrationRequestDTO, CreditDTO creditDTO) {
        application.getClient().setGender(finishRegistrationRequestDTO.getGender());
        application.getClient().setMaritalStatus(finishRegistrationRequestDTO.getMaritalStatus());
        application.getClient().setDependentAmount(finishRegistrationRequestDTO.getDependentAmount());
        application.getClient().setAccount(finishRegistrationRequestDTO.getAccount());
        application.getClient().getPassport().setIssueDate(finishRegistrationRequestDTO.getPassportIssueDate());
        application.getClient().getPassport().setIssueBranch(finishRegistrationRequestDTO.getPassportIssueBranch());
        Employment employment = new Employment();
        employment.setEmployerINN(finishRegistrationRequestDTO.getEmployment().getEmployerINN());
        employment.setEmploymentStatus(finishRegistrationRequestDTO.getEmployment().getEmploymentStatus());
        employment.setPosition(finishRegistrationRequestDTO.getEmployment().getPosition());
        employment.setSalary(finishRegistrationRequestDTO.getEmployment().getSalary());
        employment.setWorkExperienceCurrent(finishRegistrationRequestDTO.getEmployment().getWorkExperienceCurrent());
        employment.setWorkExperienceTotal(finishRegistrationRequestDTO.getEmployment().getWorkExperienceTotal());
        application.getClient().setEmployment(employment);
        final Credit credit = application.getCredit();
        credit.setAmount(creditDTO.getAmount());
        credit.setTerm(creditDTO.getTerm());
        credit.setMonthlyPayment(creditDTO.getMonthlyPayment());
        credit.setRate(creditDTO.getRate());
        credit.setPsk(creditDTO.getPsk());
        credit.setIsInsuranceEnabled(creditDTO.getIsInsuranceEnabled());
        credit.setIsSalaryClient(creditDTO.getIsSalaryClient());
        final List<PaymentScheduleElement> listPayments = creditDTO.getPaymentSchedule();
        for (int i = 0; i < listPayments.size(); i++) {
            PaymentScheduleElement paymentScheduleElement = listPayments.get(i);
            PaymentScheduleElementDTOEntity paymentScheduleElementDTOEntity = new PaymentScheduleElementDTOEntity();
            paymentScheduleElementDTOEntity.setNumber(paymentScheduleElement.getNumber());
            paymentScheduleElementDTOEntity.setDate(paymentScheduleElement.getDate());
            paymentScheduleElementDTOEntity.setTotalPayment(paymentScheduleElement.getTotalPayment());
            paymentScheduleElementDTOEntity.setInterestPayment(paymentScheduleElement.getInterestPayment());
            paymentScheduleElementDTOEntity.setDebtPayment(paymentScheduleElement.getDebtPayment());
            paymentScheduleElementDTOEntity.setRemainingDebt(paymentScheduleElement.getRemainingDebt());
            paymentScheduleElementDTOEntityRepository.save(paymentScheduleElementDTOEntity);
            application.getCredit().getPaymentSchedule().add(paymentScheduleElementDTOEntity);
        }
        log.info("Сетим в сущности Client и Credit конечные данные");
        applicationRepository.save(application);

        return null;
    }


    public ApplicationEntityService(ApplicationRepository applicationRepository,
                                    ApplicationStatusHistoryDTOEntityRepository applicationStatusHistoryDTOEntityRepository,
                                    LoanOfferDTOEntityRepository loanOfferDTOEntityRepository,
                                    PaymentScheduleElementDTOEntityRepository paymentScheduleElementDTOEntityRepository) {
        this.applicationRepository = applicationRepository;
        this.applicationStatusHistoryDTOEntityRepository = applicationStatusHistoryDTOEntityRepository;
        this.loanOfferDTOEntityRepository = loanOfferDTOEntityRepository;
        this.paymentScheduleElementDTOEntityRepository = paymentScheduleElementDTOEntityRepository;
    }
}
