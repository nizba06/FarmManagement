package com.farmmanagement.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class SeasonReport {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Farm farm;

    private String cropType;
    private double plantingArea;
    private double expectedProduct;
    private double actualProduct;

    private String season;
}
