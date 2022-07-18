package ru.shumbasov.deal.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.shumbasov.deal.entity.Application;

public interface ApplicationRepository extends JpaRepository<Application , Long> {
}
