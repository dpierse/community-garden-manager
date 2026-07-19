package com.dannypierse.communitygardenmanager.model;

import jakarta.persistence.*;

@Entity
@Table(name = "garden_plots")
public class GardenPlot {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String plotName;

    private boolean available;

    @ManyToOne
    @JoinColumn(name = "owner_id")
    private User owner;

    public GardenPlot() {
    }

    public GardenPlot(String plotName, boolean available) {
        this.plotName = plotName;
        this.available = available;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPlotName() {
        return plotName;
    }

    public void setPlotName(String plotName) {
        this.plotName = plotName;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }

    public User getOwner() {
        return owner;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }
}