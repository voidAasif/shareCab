package com.example.shareCab.repository;

import com.example.shareCab.model.Ride;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RideRepository extends JpaRepository<Ride, Long> {
    List<Ride> findBySourceAndDestination(String source, String destination);
}

