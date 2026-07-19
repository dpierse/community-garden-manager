package com.dannypierse.communitygardenmanager.service;

import com.dannypierse.communitygardenmanager.model.GardenPlot;
import com.dannypierse.communitygardenmanager.model.User;
import com.dannypierse.communitygardenmanager.repository.GardenPlotRepository;
import com.dannypierse.communitygardenmanager.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GardenPlotService {

    @Autowired
    private GardenPlotRepository gardenPlotRepository;

    @Autowired
    private UserRepository userRepository;

    public GardenPlot savePlot(GardenPlot gardenPlot) {
        return gardenPlotRepository.save(gardenPlot);
    }

    public List<GardenPlot> getAllPlots() {
        return gardenPlotRepository.findAll();
    }

    public GardenPlot getPlotById(Long id) {
        return gardenPlotRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Plot not found"));
    }

    public List<GardenPlot> getPlotsByOwner(Long ownerId) {
        return gardenPlotRepository.findByOwnerId(ownerId);
    }

    public GardenPlot assignPlotToUser(Long plotId, Long userId) {

        GardenPlot plot = gardenPlotRepository.findById(plotId)
                .orElseThrow(() -> new RuntimeException("Plot not found"));

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        plot.setOwner(user);

        return gardenPlotRepository.save(plot);
    }

    public GardenPlot releasePlot(Long plotId) {

        GardenPlot plot = getPlotById(plotId);

        plot.setOwner(null);
        plot.setAvailable(true);

        return gardenPlotRepository.save(plot);
    }

    public GardenPlot unassignPlot(Long plotId) {

        GardenPlot plot = getPlotById(plotId);

        plot.setOwner(null);
        plot.setAvailable(true);

        return gardenPlotRepository.save(plot);
    }
}