package com.farmmanagement.repository;

import com.farmmanagement.model.SeasonReport;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface SeasonReportRepository  extends JpaRepository<SeasonReport, Long> {

    List<SeasonReport> findByFarm_NameAndSeason(String farmName, String season);

    List<SeasonReport> findByCropTypeAndSeason(String cropType, String season);

    Optional<SeasonReport> findByFarm_NameAndCropTypeAndSeason(String farmName, String cropType, String season);

    boolean existsBySeason(String season);
}
