package ru.shumbasov.conveyor.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.shumbasov.conveyor.dto.CreditDTO;
import ru.shumbasov.conveyor.dto.LoanApplicationRequestDTO;
import ru.shumbasov.conveyor.dto.LoanOfferDTO;
import ru.shumbasov.conveyor.service.OfferService;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/conveyor")
public class MyRestController {

    private final OfferService offerService;


    public MyRestController(OfferService offerService) {
        this.offerService = offerService;
    }



    @PostMapping("/offers")
    public List<LoanOfferDTO> offers(@RequestBody LoanApplicationRequestDTO loanApplicationRequestDTO) {
        System.out.println(loanApplicationRequestDTO.getAmount());
        System.out.println(loanApplicationRequestDTO.getBirthdate());
        System.out.println(loanApplicationRequestDTO.getFirstName());
        offerService.setLoanApplicationRequestDTO(loanApplicationRequestDTO);
        offerService.getOffers();
        return null;
    }

    @PostMapping("/calculation")
    public CreditDTO calculation() {
        return null;
    }
}
