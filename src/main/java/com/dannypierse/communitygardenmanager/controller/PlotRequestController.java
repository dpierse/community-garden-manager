package com.dannypierse.communitygardenmanager.controller;

import com.dannypierse.communitygardenmanager.model.PlotRequest;
import com.dannypierse.communitygardenmanager.service.PlotRequestService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/requests")
public class PlotRequestController {

    private final PlotRequestService plotRequestService;

    public PlotRequestController(PlotRequestService plotRequestService) {
        this.plotRequestService = plotRequestService;
    }

    @PostMapping("/create")
    public PlotRequest createRequest(@RequestBody PlotRequest request) {
        return plotRequestService.saveRequest(request);
    }

    @GetMapping("/all")
    public List<PlotRequest> getAllRequests() {
        return plotRequestService.getAllRequests();
    }

    @GetMapping("/user/{userId}")
    public List<PlotRequest> getRequestsByUser(@PathVariable Long userId) {
        return plotRequestService.getRequestsByUser(userId);
    }

    @PutMapping("/{id}/approve")
    public PlotRequest approveRequest(@PathVariable Long id) {
        return plotRequestService.approveRequest(id);
    }

    @PutMapping("/{id}/reject")
    public PlotRequest rejectRequest(@PathVariable Long id) {
        return plotRequestService.rejectRequest(id);
    }

}