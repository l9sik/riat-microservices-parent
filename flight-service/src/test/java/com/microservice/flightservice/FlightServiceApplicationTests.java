package com.microservice.flightservice;

import com.microservice.flightservice.dto.FlightRequest;
import com.microservice.flightservice.model.Flight;
import com.microservice.flightservice.repository.FlightRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.math.BigDecimal;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest
@AutoConfigureMockMvc
class FlightServiceApplicationTests {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    FlightRepository flightRepository;

    @Test
    void getFlight_shouldReturnOKStatus() throws Exception {
        // Вызовите метод микросервиса
        mockMvc.perform(MockMvcRequestBuilders.get("/api/flight")
                        .param("origin", "NYC")
                        .param("destination", "LAX")
                        .param("date", "2023-12-15"))
                .andExpect(status().isOk());

     }
}

