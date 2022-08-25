package ru.shumbasov.deal.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.shumbasov.deal.dto.FinishRegistrationRequestDTO;
import ru.shumbasov.deal.dto.LoanApplicationRequestDTO;
import ru.shumbasov.deal.dto.LoanOfferDTO;
import ru.shumbasov.deal.service.ApplicationService;
import ru.shumbasov.deal.service.CalculationService;
import ru.shumbasov.deal.service.OfferService;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/deal")
public class DealRestController {
    private final ApplicationService applicationService;
    private final OfferService offerService;
    private final CalculationService calculationService;

    @PostMapping("/application")
    public ResponseEntity<List<LoanOfferDTO>> getAllOffers(@Valid @RequestBody LoanApplicationRequestDTO loanApplicationRequestDTO) {
        applicationService.setLoanApplicationRequestDTO(loanApplicationRequestDTO);
        return ResponseEntity.ok(applicationService.getOffers());
    }

    @PutMapping("/offer")
    public void getOffer(@RequestBody LoanOfferDTO loanOfferDTO) {
        offerService.setLoanOfferDTO(loanOfferDTO);
        offerService.saveOffer();
    }

    @PutMapping("/calculate/{applicationId}")
    public void calculation(@RequestBody @Valid FinishRegistrationRequestDTO finishRegistrationRequestDTO,
                            @PathVariable("applicationId") Long applicationId) {
        calculationService.setFinishRegistrationRequestDTO(finishRegistrationRequestDTO);
        calculationService.calculateAndFinishRegistration(applicationId);
    }

    public DealRestController(ApplicationService applicationService, OfferService offerService, CalculationService calculationService) {
        this.applicationService = applicationService;
        this.offerService = offerService;
        this.calculationService = calculationService;
    }
}
