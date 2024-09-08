package com.farmmanagement.dto;

import lombok.Data;

@Data
public class PlantingRequest {
    private String farmName;
    private String cropType;
    private double plantingArea;
    private double expectedProduct;
    private String season;
}
