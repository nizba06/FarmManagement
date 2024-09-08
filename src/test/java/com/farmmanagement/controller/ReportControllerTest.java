package com.farmmanagement.controller;

import com.farmmanagement.model.Farm;
import com.farmmanagement.model.SeasonReport;
import com.farmmanagement.repository.SeasonReportRepository;
import com.farmmanagement.service.SeasonReportService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.List;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ReportController.class)
public class ReportControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private SeasonReportService seasonReportService;

    @MockBean
    private SeasonReportRepository seasonReportRepository;

    private SeasonReport getSampleReport() {
        SeasonReport report = new SeasonReport();
        report.setId(1L);
        report.setFarm(new Farm(1L, "MyFarm"));  // Ensure this is not null
        report.setCropType("Corn");
        report.setPlantingArea(100.0);
        report.setExpectedProduct(150.0);
        report.setActualProduct(140.0);
        report.setSeason("Spring 2024");
        return report;
    }

    @Test
    public void testGetFarmReport() throws Exception {
        List<SeasonReport> reports = Arrays.asList(getSampleReport());
        when(seasonReportService.getFarmReport(anyString(), anyString())).thenReturn(reports);

        mockMvc.perform(get("/api/reports/farm/MyFarm/season/Spring 2024"))
                .andDo(result -> System.out.println(result.getResponse().getContentAsString()))  // Print the response body
                .andExpect(status().isOk());
    }
}
