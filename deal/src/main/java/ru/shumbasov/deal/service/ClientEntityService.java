package ru.shumbasov.deal.service;

import org.springframework.stereotype.Service;
import ru.shumbasov.deal.dto.LoanApplicationRequestDTO;
import ru.shumbasov.deal.entity.Client;
import ru.shumbasov.deal.entity.Passport;
import ru.shumbasov.deal.repository.ClientRepository;

@Service
public class ClientEntityService {
    private final ClientRepository clientRepository;

      Client createClient(LoanApplicationRequestDTO loanApplicationRequestDTO){
        Client client = new Client();
        client.setLastName(loanApplicationRequestDTO.getLastName());
        client.setFirstName(loanApplicationRequestDTO.getFirstName());
        client.setMiddleName(loanApplicationRequestDTO.getMiddleName());
        client.setBirthDate(loanApplicationRequestDTO.getBirthdate());
        client.setEmail(loanApplicationRequestDTO.getEmail());
        Passport passport = new Passport();
        passport.setSeries(loanApplicationRequestDTO.getPassportSeries());
        passport.setNumber(loanApplicationRequestDTO.getPassportNumber());
        client.setPassport(passport);
        return clientRepository.save(client);
    }

    public ClientEntityService(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }
}
