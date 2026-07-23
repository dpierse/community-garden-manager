package com.dannypierse.communitygardenmanager.model;

import jakarta.persistence.*;

@Entity
public class PlotRequest {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false)
    private User user;

    @ManyToOne(optional = false)
    private GardenPlot plot;

    private String status;


    public PlotRequest() {
    }


    public PlotRequest(User user, GardenPlot plot, String status) {
        this.user = user;
        this.plot = plot;
        this.status = status;
    }


    public Long getId() {
        return id;
    }


    public void setId(Long id) {
        this.id = id;
    }


    public User getUser() {
        return user;
    }


    public void setUser(User user) {
        this.user = user;
    }


    public GardenPlot getPlot() {
        return plot;
    }


    public void setPlot(GardenPlot plot) {
        this.plot = plot;
    }


    public String getStatus() {
        return status;
    }


    public void setStatus(String status) {
        this.status = status;
    }
}