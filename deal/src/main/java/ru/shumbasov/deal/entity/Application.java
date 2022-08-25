package ru.shumbasov.deal.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.shumbasov.deal.dto.ApplicationStatusHistoryDTO;
import ru.shumbasov.deal.enums.ApplicationStatus;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Application {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(cascade = {CascadeType.PERSIST , CascadeType.DETACH , CascadeType.REFRESH , CascadeType.MERGE})
    @JoinColumn(name = "client_id")
    private Client client;

    @OneToOne
    @JoinColumn(name = "credit_id")
    private Credit credit;

    private ApplicationStatus applicationStatus;

    private LocalDate creationDate;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "appliedoffer_id")
    private LoanOfferDTOEntity appliedOffer;

    private LocalDate signDate;

    private String sesCode;

    @OneToMany
    @JoinColumn(name = "application_id")
    private List<ApplicationStatusHistoryDTOEntity> statusHistory;
}


