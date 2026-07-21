package com.dannypierse.communitygardenmanager.service;

import com.dannypierse.communitygardenmanager.model.PlotRequest;
import com.dannypierse.communitygardenmanager.repository.PlotRequestRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PlotRequestService {

    private final PlotRequestRepository plotRequestRepository;
    private final GardenPlotService gardenPlotService;

    public PlotRequestService(
            PlotRequestRepository plotRequestRepository,
            GardenPlotService gardenPlotService
    ) {
        this.plotRequestRepository = plotRequestRepository;
        this.gardenPlotService = gardenPlotService;
    }

    public PlotRequest saveRequest(PlotRequest request) {
        return plotRequestRepository.save(request);
    }

    public List<PlotRequest> getAllRequests() {
        return plotRequestRepository.findAll();
    }

    public List<PlotRequest> getRequestsByUser(Long userId) {
        return plotRequestRepository.findByUserId(userId);
    }

    public PlotRequest approveRequest(Long id) {

        PlotRequest request = plotRequestRepository.findById(id)
                .orElseThrow();

        request.setStatus("APPROVED");

        gardenPlotService.assignPlotToUser(
                request.getPlot().getId(),
                request.getUser().getId()
        );

        return plotRequestRepository.save(request);
    }

    public PlotRequest rejectRequest(Long id) {

        PlotRequest request = plotRequestRepository.findById(id)
                .orElseThrow();

        request.setStatus("REJECTED");

        return plotRequestRepository.save(request);
    }

}