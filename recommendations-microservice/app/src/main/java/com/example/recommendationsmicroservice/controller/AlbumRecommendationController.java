package com.example.recommendationsmicroservice.controller;

import com.example.recommendationsmicroservice.model.AlbumRecommendation;
import com.example.recommendationsmicroservice.repository.AlbumRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
@RestController
@RequestMapping(value ="/albumRecommendation")
public class AlbumRecommendationController {
    @Autowired
    private AlbumRepository repo;

    //GET by id
    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public AlbumRecommendation getAlbumById(@PathVariable Long id){
        Optional<AlbumRecommendation> returnVal = repo.findById(id);
        if(returnVal.isPresent() == false){
            throw new IllegalArgumentException("No Album with that id"+id);
        }
        return returnVal.get();
    }
    //GET all
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<AlbumRecommendation> getAllAlbums() {
        return repo.findAll();
    }

    //CREATE a new album
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public AlbumRecommendation newAlbum(@RequestBody AlbumRecommendation album){
        return repo.save(album);
    }

    //UPDATE an album
    @PutMapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public AlbumRecommendation updateAlbum(@RequestBody AlbumRecommendation album){
        return repo.save(album);
    }

    //DELETE an album
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteAlbum(@PathVariable Long id){
        repo.deleteById(id);
    }
}
