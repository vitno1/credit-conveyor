package ru.shumbasov.conveyor.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.shumbasov.conveyor.dto.CreditDTO;
import ru.shumbasov.conveyor.dto.LoanApplicationRequestDTO;
import ru.shumbasov.conveyor.dto.LoanOfferDTO;
import ru.shumbasov.conveyor.service.OfferService;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/conveyor")
public class MyRestController {

    private final OfferService offerService;


    public MyRestController(OfferService offerService) {
        this.offerService = offerService;
    }


    @PostMapping("/offers")
    public List<LoanOfferDTO> offers(@Valid @RequestBody LoanApplicationRequestDTO loanApplicationRequestDTO) {
        offerService.setLoanApplicationRequestDTO(loanApplicationRequestDTO);
        return offerService.getOffers();
    }

    @PostMapping("/calculation")
    public CreditDTO calculation() {
        return null;
    }
}
