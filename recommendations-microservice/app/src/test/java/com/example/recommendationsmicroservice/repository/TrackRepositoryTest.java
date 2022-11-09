package com.example.recommendationsmicroservice.repository;

import com.example.recommendationsmicroservice.model.TrackRecommendation;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

import static org.junit.Assert.*;
@RunWith(SpringRunner.class)
@SpringBootTest
public class TrackRepositoryTest {
    @Autowired
    private TrackRepository repo;

    @Before
    public void setUp() {
        // clear out database
        repo.deleteAll();
    }

    @Test
    public void shouldCreateGetAndDeleteCoffee() {
        TrackRecommendation trackRecommendation = new TrackRecommendation();
        trackRecommendation.setLiked(false);
        trackRecommendation.setUserId(1L);
        trackRecommendation.setTrackId(1L);

        trackRecommendation = repo.save(trackRecommendation);

        TrackRecommendation whatIGot = repo.findById(trackRecommendation.getTrackRecommendationId()).get();

        assertEquals(trackRecommendation, whatIGot);

        repo.deleteById(trackRecommendation.getTrackRecommendationId());

        Optional<TrackRecommendation> shouldBeEmptyOptional = repo.findById(trackRecommendation.getTrackRecommendationId());
        assertEquals(false, shouldBeEmptyOptional.isPresent());
    }


}