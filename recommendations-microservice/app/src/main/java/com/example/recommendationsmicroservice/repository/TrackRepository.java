package com.example.recommendationsmicroservice.repository;

import com.example.recommendationsmicroservice.model.TrackRecommendation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TrackRepository extends JpaRepository<TrackRecommendation, Long> {
}
