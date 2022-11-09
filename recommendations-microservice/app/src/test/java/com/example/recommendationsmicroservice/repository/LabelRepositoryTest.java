package com.example.recommendationsmicroservice.repository;

import com.example.recommendationsmicroservice.model.LabelRecommendation;
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
public class LabelRepositoryTest {
    @Autowired
    private LabelRepository repo;

    @Before
    public void setUp() {
        // clear out database
        repo.deleteAll();
    }

    @Test
    public void shouldCreateGetAndDeleteCoffee() {
        LabelRecommendation labelRecommendation = new LabelRecommendation();
        labelRecommendation.setLiked(false);
        labelRecommendation.setUserId(1L);
        labelRecommendation.setLabelId(1L);

        labelRecommendation = repo.save(labelRecommendation);

        LabelRecommendation whatIGot = repo.findById(labelRecommendation.getLabelRecommendationId()).get();

        assertEquals(labelRecommendation, whatIGot);

        repo.deleteById(labelRecommendation.getLabelRecommendationId());

        Optional<LabelRecommendation> shouldBeEmptyOptional = repo.findById(labelRecommendation.getLabelRecommendationId());
        assertEquals(false, shouldBeEmptyOptional.isPresent());
    }

}