package ru.shumbasov.conveyor.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.shumbasov.conveyor.dto.CreditDTO;
import ru.shumbasov.conveyor.dto.LoanApplicationRequestDTO;
import ru.shumbasov.conveyor.dto.LoanOfferDTO;
import ru.shumbasov.conveyor.dto.ScoringDataDTO;
import ru.shumbasov.conveyor.service.CalculationService;
import ru.shumbasov.conveyor.service.OfferService;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/conveyor")
public class MyRestController {

    private final OfferService offerService;
    private final CalculationService calculationService;

    public MyRestController(OfferService offerService, CalculationService calculationService) {
        this.offerService = offerService;
        this.calculationService = calculationService;
    }


    @PostMapping("/offers")
    public ResponseEntity<List<LoanOfferDTO>> offers(@Valid @RequestBody LoanApplicationRequestDTO loanApplicationRequestDTO) {
        offerService.setLoanApplicationRequestDTO(loanApplicationRequestDTO);
        return new ResponseEntity<>(offerService.getOffers(), HttpStatus.OK);
    }

    @PostMapping("/calculation")
    public ResponseEntity<CreditDTO> calculation(@Valid @RequestBody ScoringDataDTO scoringDataDTO) {
        calculationService.setScoringDataDTO(scoringDataDTO);
        calculationService.checkScoring();
        return new ResponseEntity<>(calculationService.getCreditDTO(), HttpStatus.OK);
    }
}
