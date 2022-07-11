package ru.shumbasov.conveyor.controller;


import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import ru.shumbasov.conveyor.service.CalculationService;
import ru.shumbasov.conveyor.service.OfferService;

@WebMvcTest(MyRestController.class)
public class MyRestControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;
    @MockBean
    private OfferService offerService;
    @MockBean
    private CalculationService calculationService;
}
