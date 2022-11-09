package com.example.recommendationsmicroservice.controller;


import com.example.recommendationsmicroservice.model.ArtistRecommendation;
import com.example.recommendationsmicroservice.repository.ArtistRepository;
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
@WebMvcTest(ArtistRecommendationController.class)
@AutoConfigureMockMvc(addFilters = false)
public class ArtistRecommendationControllerTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    private ArtistRepository repo;
    private ObjectMapper mapper = new ObjectMapper();

    ArtistRecommendation inputArtist;
    ArtistRecommendation outputArtist;
    List<ArtistRecommendation> allArtist = new ArrayList<>();

    @Before
    public void setup(){
        inputArtist = new ArtistRecommendation();
        inputArtist.setArtistId(1L);
        inputArtist.setLiked(true);
        inputArtist.setUserId(1L);

        outputArtist = new ArtistRecommendation();
        outputArtist.setArtistRecommendationId(1L);
        outputArtist.setArtistId(1L);
        outputArtist.setLiked(true);
        outputArtist.setUserId(1L);

        allArtist = new ArrayList<>(Arrays.asList(
                outputArtist
        ));

    }
    //GET all artists
    @Test
    public void shouldReturnAllArtistOnGetRequest() throws Exception {
        String outputJson = mapper.writeValueAsString(allArtist);

        doReturn(allArtist).
                when // conditional
                (repo).findAll(); //method we want to test

        mockMvc.perform(
                        get("/artistRecommendation"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect((content().json(outputJson)));
    }
    //GET artist by id
    @Test
    public void shouldReturnArtistById() throws Exception{
        String outputJson = mapper.writeValueAsString(outputArtist);

        doReturn(Optional.of(outputArtist)).when(repo).findById(1L);

        mockMvc.perform(
                        get("/artistRecommendation/1"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect((content().json(outputJson)));

    }
    //CREATE an artist
    @Test
    public void shouldCreateNewArtistOnPostRequest() throws Exception {

        String inputArtistJson = mapper.writeValueAsString(inputArtist);
        String outputArtistJson = mapper.writeValueAsString(outputArtist);

        doReturn(outputArtist).when(repo).save(inputArtist);

        // Act
        mockMvc.perform(post("/artistRecommendation")
                        .content(inputArtistJson)
                        .contentType(MediaType.APPLICATION_JSON)
                )
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(content().json(outputArtistJson));
    }
    //    Update/PUT a artist
    @Test
    public void shouldUpdateArtistByIdAndReturn204StatusCode() throws Exception {
        inputArtist.setArtistRecommendationId(1L);
        inputArtist.setLiked(false);
        String inputJson = mapper.writeValueAsString(inputArtist);

        doReturn(Optional.of(outputArtist)).when(repo).findById(1L);

        mockMvc.perform(
                        put("/artistRecommendation")
//                                .andDo(print()
                                .content(inputJson)
                                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());
    }
    //   Delete a artist
    @Test
    public void shouldDeleteByIdAndReturn204StatusCode() throws Exception {
        mockMvc.perform(delete("/artistRecommendation/1")).andExpect(status().isNoContent());
    }

}