package com.dannypierse.communitygardenmanager.service;

import com.dannypierse.communitygardenmanager.model.GardenPlot;
import com.dannypierse.communitygardenmanager.repository.GardenPlotRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GardenPlotService {

    @Autowired
    private GardenPlotRepository gardenPlotRepository;

    public GardenPlot savePlot(GardenPlot gardenPlot) {
        return gardenPlotRepository.save(gardenPlot);
    }

    public List<GardenPlot> getAllPlots() {
        return gardenPlotRepository.findAll();
    }
}