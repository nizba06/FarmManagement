package com.farmmanagement.controller;

import com.farmmanagement.dto.HarvestingRequest;
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

@WebMvcTest(HarvestingController.class)
public class HarvestingControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private SeasonReportService seasonReportService;

    @Test
    public void testAddHarvestedData() throws Exception {
        doNothing().when(seasonReportService).addHarvestedData(any(HarvestingRequest.class));

        HarvestingRequest harvestingRequest = new HarvestingRequest();
        harvestingRequest.setFarmName("MyFarm");
        harvestingRequest.setCropType("Corn");
        harvestingRequest.setActualProduct(140);
        harvestingRequest.setSeason("Spring 2024");

        mockMvc.perform(post("/api/harvesting")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(harvestingRequest)))
                .andExpect(status().isOk())
                .andExpect(content().string("Harvesting data added successfully"));

        verify(seasonReportService).addHarvestedData(any(HarvestingRequest.class));
    }

    private static String asJsonString(final Object obj) throws Exception {
        return new ObjectMapper().writeValueAsString(obj);
    }
}
