package com.example.recommendationsmicroservice.controller;

import com.example.recommendationsmicroservice.model.TrackRecommendation;
import com.example.recommendationsmicroservice.repository.TrackRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
@RestController
@RequestMapping(value ="/trackRecommendation")
public class TrackRecommendationController {
    @Autowired
    private TrackRepository repo;

    //GET by id
    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public TrackRecommendation getTrackById(@PathVariable Long id){
        Optional<TrackRecommendation> returnVal = repo.findById(id);
        if(returnVal.isPresent() == false){
            throw new IllegalArgumentException("No Track with that id"+id);
        }
        return returnVal.get();
    }
    //GET all
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<TrackRecommendation> getAllTracks() {
        return repo.findAll();
    }

    //CREATE a new track
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public TrackRecommendation newTrack(@RequestBody TrackRecommendation track){
        return repo.save(track);
    }

    //UPDATE a track
    @PutMapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public TrackRecommendation updateTrack(@RequestBody TrackRecommendation track){
        return repo.save(track);
    }

    //DELETE a track
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteTrack(@PathVariable Long id){
        repo.deleteById(id);
    }
}
