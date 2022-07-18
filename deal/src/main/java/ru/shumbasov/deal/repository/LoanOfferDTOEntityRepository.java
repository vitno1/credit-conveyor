package ru.shumbasov.deal.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.shumbasov.deal.entity.LoanOfferDTOEntity;


public interface LoanOfferDTOEntityRepository extends JpaRepository<LoanOfferDTOEntity, Long> {
}
