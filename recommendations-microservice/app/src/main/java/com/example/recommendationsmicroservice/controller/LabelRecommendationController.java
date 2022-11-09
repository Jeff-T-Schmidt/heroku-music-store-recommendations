package com.example.recommendationsmicroservice.controller;

import com.example.recommendationsmicroservice.model.LabelRecommendation;
import com.example.recommendationsmicroservice.repository.LabelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
@RestController
@RequestMapping(value ="/labelRecommendation")
public class LabelRecommendationController {
    @Autowired
    private LabelRepository repo;

    //GET by id
    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public LabelRecommendation getLabelById(@PathVariable Long id){
        Optional<LabelRecommendation> returnVal = repo.findById(id);
        if(returnVal.isPresent() == false){
            throw new IllegalArgumentException("No Label with that id"+id);
        }
        return returnVal.get();
    }
    //GET all
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<LabelRecommendation> getAllLabels() {
        return repo.findAll();
    }

    //CREATE a new label
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public LabelRecommendation newLabel(@RequestBody LabelRecommendation label){
        return repo.save(label);
    }

    //UPDATE an label
    @PutMapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public LabelRecommendation updateLabel(@RequestBody LabelRecommendation label){
        return repo.save(label);
    }

    //DELETE an label
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteLabel(@PathVariable Long id){
        repo.deleteById(id);
    }
}
