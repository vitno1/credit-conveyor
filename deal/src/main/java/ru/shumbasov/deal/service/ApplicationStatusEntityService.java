package ru.shumbasov.deal.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import ru.shumbasov.deal.repository.ApplicationStatusHistoryDTOEntityRepository;



@Service
@Slf4j
public class ApplicationStatusEntityService {
    private final ApplicationStatusHistoryDTOEntityRepository applicationStatusHistoryDTOEntityRepository;

    public ApplicationStatusEntityService(ApplicationStatusHistoryDTOEntityRepository applicationStatusHistoryDTOEntityRepository) {
        this.applicationStatusHistoryDTOEntityRepository = applicationStatusHistoryDTOEntityRepository;
    }
}
