package com.dannypierse.communitygardenmanager.repository;

import com.dannypierse.communitygardenmanager.model.GardenPlot;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GardenPlotRepository extends JpaRepository<GardenPlot, Long> {

}
