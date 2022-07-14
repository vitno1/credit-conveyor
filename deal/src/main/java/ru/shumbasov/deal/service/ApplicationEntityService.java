package ru.shumbasov.deal.service;

import org.springframework.stereotype.Service;
import ru.shumbasov.deal.entity.Application;
import ru.shumbasov.deal.entity.Client;
import ru.shumbasov.deal.enums.ApplicationStatus;
import ru.shumbasov.deal.repository.ApplicationRepository;

import java.time.LocalDate;

@Service
public class ApplicationEntityService {
    private final ApplicationRepository applicationRepository;

    Application createApplication(Client client) {
        Application application = new Application();
        application.setClient(client);
        application.setApplicationStatus(ApplicationStatus.PREAPPROVAL);
        application.setCreationDate(LocalDate.now());
        return applicationRepository.save(application);
    }

    public ApplicationEntityService(ApplicationRepository applicationRepository) {
        this.applicationRepository = applicationRepository;
    }
}
