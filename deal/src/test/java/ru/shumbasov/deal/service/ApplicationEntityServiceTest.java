package ru.shumbasov.deal.service;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.shumbasov.deal.dto.*;
import ru.shumbasov.deal.entity.*;
import ru.shumbasov.deal.enums.ApplicationStatus;
import ru.shumbasov.deal.repository.ApplicationRepository;
import ru.shumbasov.deal.repository.ApplicationStatusHistoryDTOEntityRepository;
import ru.shumbasov.deal.repository.LoanOfferDTOEntityRepository;
import ru.shumbasov.deal.repository.PaymentScheduleElementDTOEntityRepository;

import java.util.ArrayList;
import java.util.List;

@ExtendWith(MockitoExtension.class)
public class ApplicationEntityServiceTest {
    @InjectMocks
    private ApplicationEntityService applicationEntityService;
    @Mock
    private ApplicationRepository applicationRepository;
    @Mock
    private ApplicationStatusHistoryDTOEntityRepository applicationStatusHistoryDTOEntityRepository;
    @Mock
    private LoanOfferDTOEntityRepository loanOfferDTOEntityRepository;
    @Mock
    private PaymentScheduleElementDTOEntityRepository paymentScheduleElementDTOEntityRepository;

    @Test
    void createApp() {
        Mockito.when(applicationRepository.save(ArgumentMatchers.any())).thenReturn(new Application());
        Application application = applicationEntityService.createApplication(new Client());
        assertThat(application).isNotNull();
    }

    @Test
    void setCredit() {
        Application application = new Application();
        application.setCredit(new Credit());
        Mockito.when(applicationRepository.save(ArgumentMatchers.any())).thenReturn(application);
        applicationEntityService.setCredit(new Application(), new Credit());
    }

    @Test
    void findById() {
        Application application = new Application();
        application.setId(8L);
        Mockito.when(applicationRepository.findById(ArgumentMatchers.any())).thenReturn(java.util.Optional.of(application));
        Application byId = applicationEntityService.findById(1000L);
        System.out.println(byId);
        assertThat(byId).isNotNull();
    }

    @Test
    void applicationStatus() {
        Application application = new Application();
        application.setStatusHistory(new ArrayList<>());
        System.out.println("application = " + application);
        Mockito.when(applicationRepository.save(ArgumentMatchers.any())).thenReturn(application);
        Application app = applicationEntityService.setStatus(application, ApplicationStatus.CLIENT_DENIED);
        assertThat(app).isNotNull();
    }

    @Test
    void setAppliedOffer() {
        Application application = new Application();
        Mockito.when(loanOfferDTOEntityRepository.save(new LoanOfferDTOEntity())).thenReturn(new LoanOfferDTOEntity());
        Mockito.when(applicationRepository.save(ArgumentMatchers.any())).thenReturn(application);
        applicationEntityService.setAppliedOffer(application, new LoanOfferDTO());
    }
    @Test
    void setFinishData(){
        Application application = new Application();
        FinishRegistrationRequestDTO finishRegistrationRequestDTO = new FinishRegistrationRequestDTO();
        EmploymentDTO employmentDTO = new EmploymentDTO();
        Credit credit = new Credit();
        CreditDTO creditDTO = new CreditDTO();
        Client client = new Client();
        Passport passport = new Passport();
        List<PaymentScheduleElement> paymentScheduleElement = new ArrayList<>();
        application.setCredit(credit);
        finishRegistrationRequestDTO.setEmployment(employmentDTO);
        application.setClient(client);
        application.getClient().setPassport(passport);
        creditDTO.setPaymentSchedule(paymentScheduleElement);
        Mockito.when(applicationRepository.save(ArgumentMatchers.any())).thenReturn(application);
        applicationEntityService.setFinishData(application , finishRegistrationRequestDTO , creditDTO);
    }


}
