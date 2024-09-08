package com.farmmanagement.controller;

import com.farmmanagement.dto.HarvestingRequest;
import com.farmmanagement.service.SeasonReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/harvesting")
public class HarvestingController {
    @Autowired
    private SeasonReportService seasonReportService;

    @PostMapping
    public ResponseEntity<String> addHarvestedData(@RequestBody HarvestingRequest request) {
        seasonReportService.addHarvestedData(request);
        return ResponseEntity.ok("Harvesting data added successfully");
    }
}
