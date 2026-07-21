package com.dannypierse.communitygardenmanager.repository;

import com.dannypierse.communitygardenmanager.model.PlotRequest;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PlotRequestRepository extends JpaRepository<PlotRequest, Long> {

    List<PlotRequest> findByUserId(Long userId);

}