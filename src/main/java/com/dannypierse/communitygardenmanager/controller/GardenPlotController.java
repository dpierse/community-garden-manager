package com.dannypierse.communitygardenmanager.controller;

import com.dannypierse.communitygardenmanager.model.GardenPlot;
import com.dannypierse.communitygardenmanager.service.GardenPlotService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/plots")
public class GardenPlotController {

    @Autowired
    private GardenPlotService gardenPlotService;

    @PostMapping("/add")
    public GardenPlot addPlot(@RequestBody GardenPlot gardenPlot) {
        return gardenPlotService.savePlot(gardenPlot);
    }

    @GetMapping("/all")
    public List<GardenPlot> getAllPlots() {
        return gardenPlotService.getAllPlots();
    }

    @GetMapping("/create-test-plots")
    public String createTestPlots() {

        GardenPlot plot1 = new GardenPlot();
        plot1.setPlotName("Plot 1");
        plot1.setAvailable(true);

        GardenPlot plot2 = new GardenPlot();
        plot2.setPlotName("Plot 2");
        plot2.setAvailable(true);

        GardenPlot plot3 = new GardenPlot();
        plot3.setPlotName("Plot 3");
        plot3.setAvailable(false);

        gardenPlotService.savePlot(plot1);
        gardenPlotService.savePlot(plot2);
        gardenPlotService.savePlot(plot3);

        return "Test plots created successfully";
    }
}