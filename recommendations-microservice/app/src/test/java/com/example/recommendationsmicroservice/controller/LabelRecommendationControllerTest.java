package com.example.recommendationsmicroservice.controller;

import com.example.recommendationsmicroservice.model.LabelRecommendation;
import com.example.recommendationsmicroservice.repository.LabelRepository;
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
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(LabelRecommendationController.class)
@AutoConfigureMockMvc(addFilters = false)
public class LabelRecommendationControllerTest {
    @Autowired
    MockMvc mockMvc;

    @MockBean
    private LabelRepository repo;
    private ObjectMapper mapper = new ObjectMapper();

    LabelRecommendation inputLabel;
    LabelRecommendation outputLabel;
    List<LabelRecommendation> allLabel = new ArrayList<>();

    @Before
    public void setup(){
        inputLabel = new LabelRecommendation();
        inputLabel.setLabelId(1L);
        inputLabel.setLiked(true);
        inputLabel.setUserId(1L);

        outputLabel = new LabelRecommendation();
        outputLabel.setLabelRecommendationId(1L);
        outputLabel.setLabelId(1L);
        outputLabel.setLiked(true);
        outputLabel.setUserId(1L);

        allLabel = new ArrayList<>(Arrays.asList(
                outputLabel
        ));

    }
    //GET all labels
    @Test
    public void shouldReturnAllLabelOnGetRequest() throws Exception {
        String outputJson = mapper.writeValueAsString(allLabel);

        doReturn(allLabel).
                when // conditional
                (repo).findAll(); //method we want to test

        mockMvc.perform(
                        get("/labelRecommendation"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect((content().json(outputJson)));
    }
    //GET label by id
    @Test
    public void shouldReturnLabelById() throws Exception{
        String outputJson = mapper.writeValueAsString(outputLabel);

        doReturn(Optional.of(outputLabel)).when(repo).findById(1L);

        mockMvc.perform(
                        get("/labelRecommendation/1"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect((content().json(outputJson)));

    }
    //CREATE an label
    @Test
    public void shouldCreateNewLabelOnPostRequest() throws Exception {

        String inputLabelJson = mapper.writeValueAsString(inputLabel);
        String outputLabelJson = mapper.writeValueAsString(outputLabel);

        doReturn(outputLabel).when(repo).save(inputLabel);

        // Act
        mockMvc.perform(post("/labelRecommendation")
                        .content(inputLabelJson)
                        .contentType(MediaType.APPLICATION_JSON)
                )
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(content().json(outputLabelJson));
    }
    //    Update/PUT a label
    @Test
    public void shouldUpdateLabelByIdAndReturn204StatusCode() throws Exception {
        inputLabel.setLabelRecommendationId(1L);
        inputLabel.setLiked(false);
        String inputJson = mapper.writeValueAsString(inputLabel);

        doReturn(Optional.of(outputLabel)).when(repo).findById(1L);

        mockMvc.perform(
                        put("/labelRecommendation")
//                                .andDo(print()
                                .content(inputJson)
                                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());
    }
    //   Delete a label
    @Test
    public void shouldDeleteByIdAndReturn204StatusCode() throws Exception {
        mockMvc.perform(delete("/labelRecommendation/1")).andExpect(status().isNoContent());
    }


}