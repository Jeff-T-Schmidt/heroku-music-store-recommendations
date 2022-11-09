package com.example.recommendationsmicroservice.repository;

import com.example.recommendationsmicroservice.model.AlbumRecommendation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AlbumRepository extends JpaRepository<AlbumRecommendation, Long> {
}
