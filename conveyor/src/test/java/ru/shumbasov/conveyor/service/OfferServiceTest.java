package ru.shumbasov.conveyor.service;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ru.shumbasov.conveyor.dto.LoanApplicationRequestDTO;
import ru.shumbasov.conveyor.dto.LoanOfferDTO;

import java.math.BigDecimal;
import java.util.List;

@SpringBootTest
public class OfferServiceTest {

    @Autowired
    private OfferService offerService;

    @BeforeEach
    void prepare() {
        LoanApplicationRequestDTO loanApplicationRequestDTO = new LoanApplicationRequestDTO();
        loanApplicationRequestDTO.setAmount(new BigDecimal("10000"));
        loanApplicationRequestDTO.setTerm(18);
        offerService.setLoanApplicationRequestDTO(loanApplicationRequestDTO);
    }

    @Test
    void defaulListSize() {
        final List<LoanOfferDTO> offers = offerService.getOffers();
        assertThat(offers.size()).isEqualTo(4);
    }
}
