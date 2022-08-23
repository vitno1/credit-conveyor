package ru.shum.application.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.shum.application.dto.LoanApplicationRequestDTO;
import ru.shum.application.dto.LoanOfferDTO;
import ru.shum.application.service.ApplicationService;
import ru.shum.application.service.OfferService;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/application")
public class ApplicationController {
    private final ApplicationService applicationService;
    private final OfferService offerService;

    @PostMapping
    public ResponseEntity<List<LoanOfferDTO>> getAllOffers(@Valid @RequestBody LoanApplicationRequestDTO loanApplicationRequestDTO) {
        applicationService.setLoanApplicationRequestDTO(loanApplicationRequestDTO);
        return ResponseEntity.ok(applicationService.getOffers());
    }

    @PutMapping("/offer")
    public void pickOffer(@RequestBody LoanOfferDTO loanOfferDTO) {
        offerService.setLoanOfferDTO(loanOfferDTO);
        offerService.pickOffer();
    }

    public ApplicationController(ApplicationService applicationService, OfferService offerService) {
        this.applicationService = applicationService;
        this.offerService = offerService;
    }
}
