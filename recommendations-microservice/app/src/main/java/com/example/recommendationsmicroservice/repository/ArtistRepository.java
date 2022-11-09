package com.example.recommendationsmicroservice.repository;

import com.example.recommendationsmicroservice.model.ArtistRecommendation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ArtistRepository extends JpaRepository<ArtistRecommendation, Long> {
}
