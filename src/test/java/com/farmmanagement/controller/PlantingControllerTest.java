package com.farmmanagement.controller;

import com.farmmanagement.dto.PlantingRequest;
import com.farmmanagement.service.SeasonReportService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(PlantingController.class)
public class PlantingControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private SeasonReportService seasonReportService;

    @Test
    public void testAddPlantedData() throws Exception {
        doNothing().when(seasonReportService).addPlantedData(any(PlantingRequest.class));

        PlantingRequest plantingRequest = new PlantingRequest();
        plantingRequest.setFarmName("MyFarm");
        plantingRequest.setCropType("Corn");
        plantingRequest.setPlantingArea(100);
        plantingRequest.setExpectedProduct(150);
        plantingRequest.setSeason("Spring 2024");

        mockMvc.perform(post("/api/planting")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(plantingRequest)))
                .andExpect(status().isOk())
                .andExpect(content().string("Planting data added successfully"));

        verify(seasonReportService).addPlantedData(any(PlantingRequest.class));
    }

    private static String asJsonString(final Object obj) throws Exception {
        return new ObjectMapper().writeValueAsString(obj);
    }
}
