package ru.shumbasov.deal.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.shumbasov.deal.entity.Application;
import ru.shumbasov.deal.entity.ApplicationStatusHistoryDTOEntity;
import ru.shumbasov.deal.enums.ApplicationStatus;
import ru.shumbasov.deal.repository.ApplicationStatusHistoryDTOEntityRepository;

import java.time.LocalDate;
import java.util.List;

@Service
@Slf4j
public class ApplicationStatusEntityService {
    private final ApplicationStatusHistoryDTOEntityRepository applicationStatusHistoryDTOEntityRepository;

    public ApplicationStatusEntityService(ApplicationStatusHistoryDTOEntityRepository applicationStatusHistoryDTOEntityRepository) {
        this.applicationStatusHistoryDTOEntityRepository = applicationStatusHistoryDTOEntityRepository;
    }
}
