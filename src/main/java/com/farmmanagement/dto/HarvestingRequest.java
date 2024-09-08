package com.farmmanagement.dto;

import lombok.Data;

@Data
public class HarvestingRequest {
    private String farmName;
    private String cropType;
    private double actualProduct;
    private String season;
}
