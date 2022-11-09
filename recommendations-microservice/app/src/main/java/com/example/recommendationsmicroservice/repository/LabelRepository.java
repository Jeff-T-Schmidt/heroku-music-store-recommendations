package com.example.recommendationsmicroservice.repository;

import com.example.recommendationsmicroservice.model.LabelRecommendation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LabelRepository extends JpaRepository<LabelRecommendation, Long> {
}
