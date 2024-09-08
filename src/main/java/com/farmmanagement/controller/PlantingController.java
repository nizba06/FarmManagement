package com.farmmanagement.controller;

import com.farmmanagement.dto.PlantingRequest;
import com.farmmanagement.service.SeasonReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/planting")
public class PlantingController {
    @Autowired
    private SeasonReportService seasonReportService;

    @PostMapping
    public ResponseEntity<String> addPlantedData(@RequestBody PlantingRequest request) {
        seasonReportService.addPlantedData(request);
        return ResponseEntity.ok("Planting data added successfully");
    }
}
