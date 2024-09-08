package com.farmmanagement.controller;

import com.farmmanagement.exception.ResourceNotFoundException;
import com.farmmanagement.model.SeasonReport;
import com.farmmanagement.service.SeasonReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/reports")
public class ReportController {
    @Autowired
    private SeasonReportService seasonReportService;

    @GetMapping("/farm/{farmName}/season/{season}")
    public ResponseEntity<String> getFarmReport(@PathVariable String farmName, @PathVariable String season) {
        try {
            // Check if farm and season exist in the system
            if (!seasonReportService.doesFarmExist(farmName)) {
                throw new ResourceNotFoundException("Farm '" + farmName + "' not found.");
            }

            if (!seasonReportService.doesSeasonExist(season)) {
                throw new ResourceNotFoundException("Season '" + season + "' not found.");
            }

            List<SeasonReport> reports = seasonReportService.getFarmReport(farmName, season);

            // If no reports found for the farm and season, throw an exception
            if (reports.isEmpty()) {
                throw new ResourceNotFoundException("No reports found for farm '" + farmName + "' in season '" + season + "'.");
            }

            // Convert the SeasonReport list to a plain text response
            String response = reports.stream()
                    .map(report -> String.format(
                            "Farm: %s\nCrop Type: %s\nPlanted Area: %.2f acres\nExpected Product: %.2f tons\nActual Product: %.2f tons\nSeason: %s\n\n",
                            report.getFarm().getName(),
                            report.getCropType(),
                            report.getPlantingArea(),
                            report.getExpectedProduct(),
                            report.getActualProduct(),
                            report.getSeason()))
                    .collect(Collectors.joining("\n"));

            return ResponseEntity.ok(response);  // Return the response as plain text

        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(404).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(500).body("An unexpected error occurred.");
        }
    }
}
