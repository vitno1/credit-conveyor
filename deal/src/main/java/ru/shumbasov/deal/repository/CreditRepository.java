package ru.shumbasov.deal.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.shumbasov.deal.entity.Credit;


public interface CreditRepository extends JpaRepository<Credit, Long> {
}
