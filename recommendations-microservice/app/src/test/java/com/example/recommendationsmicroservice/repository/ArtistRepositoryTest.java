package com.example.recommendationsmicroservice.repository;

import com.example.recommendationsmicroservice.model.ArtistRecommendation;
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
public class ArtistRepositoryTest {
    @Autowired
    private ArtistRepository repo;

    @Before
    public void setUp() {
        // clear out database
        repo.deleteAll();
    }

    @Test
    public void shouldCreateGetAndDeleteCoffee() {
        ArtistRecommendation artistRecommendation = new ArtistRecommendation();
        artistRecommendation.setLiked(false);
        artistRecommendation.setUserId(1L);
        artistRecommendation.setArtistId(1L);

        artistRecommendation = repo.save(artistRecommendation);

        ArtistRecommendation whatIGot = repo.findById(artistRecommendation.getArtistRecommendationId()).get();

        assertEquals(artistRecommendation, whatIGot);

        repo.deleteById(artistRecommendation.getArtistRecommendationId());

        Optional<ArtistRecommendation> shouldBeEmptyOptional = repo.findById(artistRecommendation.getArtistRecommendationId());
        assertEquals(false, shouldBeEmptyOptional.isPresent());
    }

}