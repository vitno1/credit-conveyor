package ru.shumbasov.conveyor.dto;

import java.math.BigDecimal;
import java.util.List;

public class CreditDTO {

    private BigDecimal amount; // сумма
    private Integer term; //срок
    private BigDecimal monthlyPayment; //ежемесячный платеж
    private BigDecimal rate; //ставка
    private BigDecimal psk; //полная стоимость кредита
    private Boolean isInsuranceEnabled; //включена ли страховка
    private Boolean isSalaryClient; //зп клиента норм?
    private List<PaymentScheduleElement> paymentSchedule; //график платежей

}
