package com.example.recommendationsmicroservice.repository;

import com.example.recommendationsmicroservice.model.AlbumRecommendation;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Optional;

import static org.junit.Assert.*;
@RunWith(SpringRunner.class)
@SpringBootTest
public class AlbumRepositoryTest {
    @Autowired
    private AlbumRepository repo;

    @Before
    public void setUp() {
        // clear out database
        repo.deleteAll();
    }

    @Test
    public void shouldCreateGetAndDeleteCoffee() {
        AlbumRecommendation albumRecommendation = new AlbumRecommendation();
        albumRecommendation.setLiked(false);
        albumRecommendation.setUserId(1L);
        albumRecommendation.setAlbumId(1L);

        albumRecommendation = repo.save(albumRecommendation);

        AlbumRecommendation whatIGot = repo.findById(albumRecommendation.getAlbumRecommendationId()).get();

        assertEquals(albumRecommendation, whatIGot);

        repo.deleteById(albumRecommendation.getAlbumRecommendationId());

        Optional<AlbumRecommendation> shouldBeEmptyOptional = repo.findById(albumRecommendation.getAlbumRecommendationId());
        assertEquals(false, shouldBeEmptyOptional.isPresent());
    }
}