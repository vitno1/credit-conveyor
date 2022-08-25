package ru.shumbasov.deal.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.shumbasov.deal.entity.Passport;

public interface PassportRepository extends JpaRepository<Passport, Long> {
}
