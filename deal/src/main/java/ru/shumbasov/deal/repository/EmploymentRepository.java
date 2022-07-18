package ru.shumbasov.deal.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.shumbasov.deal.entity.Employment;


public interface EmploymentRepository  extends JpaRepository<Employment, Long> {
}
