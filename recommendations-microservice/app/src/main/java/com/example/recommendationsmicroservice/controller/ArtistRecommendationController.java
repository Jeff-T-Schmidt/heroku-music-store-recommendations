package com.example.recommendationsmicroservice.controller;

import com.example.recommendationsmicroservice.model.ArtistRecommendation;
import com.example.recommendationsmicroservice.repository.ArtistRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
@RestController
@RequestMapping(value ="/artistRecommendation")
public class ArtistRecommendationController {
    @Autowired
    private ArtistRepository repo;

    //GET by id
    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ArtistRecommendation getArtistById(@PathVariable Long id){
        Optional<ArtistRecommendation> returnVal = repo.findById(id);
        if(returnVal.isPresent() == false){
            throw new IllegalArgumentException("No Artist with that id"+id);
        }
        return returnVal.get();
    }
    //GET all
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<ArtistRecommendation> getAllArtists() {
        return repo.findAll();
    }

    //CREATE a new artist
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ArtistRecommendation newArtist(@RequestBody ArtistRecommendation artist){
        return repo.save(artist);
    }

    //UPDATE an artist
    @PutMapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ArtistRecommendation updateArtist(@RequestBody ArtistRecommendation artist){
        return repo.save(artist);
    }

    //DELETE an artist
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteArtist(@PathVariable Long id){
        repo.deleteById(id);
    }
}
