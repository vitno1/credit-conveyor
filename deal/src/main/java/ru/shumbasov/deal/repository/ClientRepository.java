package ru.shumbasov.deal.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.shumbasov.deal.entity.Client;


public interface ClientRepository extends JpaRepository<Client, Long> {
}
