package com.farmmanagement.service;

import com.farmmanagement.dto.HarvestingRequest;
import com.farmmanagement.dto.PlantingRequest;
import com.farmmanagement.model.Farm;
import com.farmmanagement.model.SeasonReport;
import com.farmmanagement.repository.FarmRepository;
import com.farmmanagement.repository.SeasonReportRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SeasonReportService {
    @Autowired
    private FarmRepository farmRepository;

    @Autowired
    private SeasonReportRepository seasonReportRepository;

    public void addPlantedData(PlantingRequest request) {
        Farm farm = farmRepository.findByName(request.getFarmName())
                .orElseGet(() -> {
                    Farm newFarm = new Farm();
                    newFarm.setName(request.getFarmName());
                    return farmRepository.save(newFarm);
                });

        SeasonReport report = new SeasonReport();
        report.setFarm(farm);
        report.setCropType(request.getCropType());
        report.setPlantingArea(request.getPlantingArea());
        report.setExpectedProduct(request.getExpectedProduct());
        report.setSeason(request.getSeason());
        seasonReportRepository.save(report);
    }

    public void addHarvestedData(HarvestingRequest request) {
        SeasonReport report = seasonReportRepository.findByFarm_NameAndCropTypeAndSeason(request.getFarmName(), request.getCropType(), request.getSeason())
                .orElseThrow(() -> new RuntimeException("No report found for farm " + request.getFarmName() + ", crop " + request.getCropType() + ", season " + request.getSeason()));

        report.setActualProduct(request.getActualProduct());
        seasonReportRepository.save(report);
    }

    public List<SeasonReport> getFarmReport(String farmName, String season) {
        return seasonReportRepository.findByFarm_NameAndSeason(farmName, season);
    }

    public boolean doesFarmExist(String farmName) {
        return farmRepository.existsByName(farmName);  // Assuming this method is defined in the FarmRepository
    }

    public boolean doesSeasonExist(String season) {
        return seasonReportRepository.existsBySeason(season);  // Assuming this method is defined in the SeasonReportRepository
    }

    public List<SeasonReport> getCropReport(String cropType, String season) {
        return seasonReportRepository.findByCropTypeAndSeason(cropType, season);
    }
}
