package ru.shumbasov.deal.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.shumbasov.deal.enums.CreditStatus;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Credit {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private BigDecimal amount;

    private Integer term;

    private BigDecimal monthlyPayment;

    private BigDecimal rate;

    private BigDecimal psk;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "credit_id")
    private List<PaymentScheduleElementDTOEntity> paymentSchedule;

    private Boolean isInsuranceEnabled;

    private Boolean isSalaryClient;


    private CreditStatus creditStatus;
}

