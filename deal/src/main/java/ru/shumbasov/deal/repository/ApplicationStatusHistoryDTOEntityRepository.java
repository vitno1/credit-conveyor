package ru.shumbasov.deal.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.shumbasov.deal.entity.ApplicationStatusHistoryDTOEntity;


public interface ApplicationStatusHistoryDTOEntityRepository extends JpaRepository<ApplicationStatusHistoryDTOEntity, Long> {


}
