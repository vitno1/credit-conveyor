package ru.shumbasov.deal.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.shumbasov.deal.dto.LoanApplicationRequestDTO;
import ru.shumbasov.deal.entity.Client;
import ru.shumbasov.deal.repository.ClientRepository;

@ExtendWith(MockitoExtension.class)
public class ClientEntityServiceTest {
    @InjectMocks
    private ClientEntityService clientEntityService;
    @Mock
    private ClientRepository clientRepository;

    @Test
    void createClient() {
        Client client = new Client();
        Mockito.when(clientRepository.save(ArgumentMatchers.any())).thenReturn(client);
        clientEntityService.createClient(new LoanApplicationRequestDTO());
    }
}
