package ru.shumbasov.deal.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.shumbasov.deal.entity.PaymentScheduleElementDTOEntity;

public interface PaymentScheduleElementDTOEntityRepository extends JpaRepository<PaymentScheduleElementDTOEntity, Long> {
}
