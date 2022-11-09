package com.example.recommendationsmicroservice.controller;

import com.example.recommendationsmicroservice.model.AlbumRecommendation;
import com.example.recommendationsmicroservice.repository.AlbumRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import static org.mockito.Mockito.doReturn;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(AlbumRecommendationController.class)
@AutoConfigureMockMvc(addFilters = false)
public class AlbumRecommendationControllerTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    private AlbumRepository repo;
    private ObjectMapper mapper = new ObjectMapper();

    AlbumRecommendation inputAlbum;
    AlbumRecommendation outputAlbum;
    List<AlbumRecommendation> allAlbums = new ArrayList<>();

    @Before
    public void setup(){
        inputAlbum = new AlbumRecommendation();
        inputAlbum.setAlbumId(1L);
        inputAlbum.setLiked(true);
        inputAlbum.setUserId(1L);

        outputAlbum = new AlbumRecommendation();
        outputAlbum.setAlbumRecommendationId(1L);
        outputAlbum.setAlbumId(1L);
        outputAlbum.setLiked(true);
        outputAlbum.setUserId(1L);

        allAlbums = new ArrayList<>(Arrays.asList(
                outputAlbum
        ));

    }
    //GET all albums
    @Test
    public void shouldReturnAllAlbumOnGetRequest() throws Exception {
        String outputJson = mapper.writeValueAsString(allAlbums);

        doReturn(allAlbums).
                when // conditional
                (repo).findAll(); //method we want to test

        mockMvc.perform(
                        get("/albumRecommendation"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect((content().json(outputJson)));
    }
    //GET album by id
    @Test
    public void shouldReturnAlbumById() throws Exception{
        String outputJson = mapper.writeValueAsString(outputAlbum);

        doReturn(Optional.of(outputAlbum)).when(repo).findById(1L);

        mockMvc.perform(
                        get("/albumRecommendation/1"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect((content().json(outputJson)));

    }
    //CREATE an album
    @Test
    public void shouldCreateNewAlbumOnPostRequest() throws Exception {

        String inputAlbumJson = mapper.writeValueAsString(inputAlbum);
        String outputAlbumJson = mapper.writeValueAsString(outputAlbum);

        doReturn(outputAlbum).when(repo).save(inputAlbum);

        // Act
        mockMvc.perform(post("/albumRecommendation")
                        .content(inputAlbumJson)
                        .contentType(MediaType.APPLICATION_JSON)
                )
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(content().json(outputAlbumJson));
    }
    //    Update/PUT a album
    @Test
    public void shouldUpdateAlbumByIdAndReturn204StatusCode() throws Exception {
        inputAlbum.setAlbumRecommendationId(1L);
        inputAlbum.setLiked(false);
        String inputJson = mapper.writeValueAsString(inputAlbum);

        doReturn(Optional.of(outputAlbum)).when(repo).findById(1L);

        mockMvc.perform(
                        put("/albumRecommendation")
//                                .andDo(print()
                                .content(inputJson)
                                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());
    }
    //   Delete a album
    @Test
    public void shouldDeleteByIdAndReturn204StatusCode() throws Exception {
        mockMvc.perform(delete("/albumRecommendation/1")).andExpect(status().isNoContent());
    }
}