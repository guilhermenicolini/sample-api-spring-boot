package com.github.guilhermenicolini.sampleapispringboot.controllers;

import com.github.guilhermenicolini.sampleapispringboot.BaseTest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class ExceptionTest extends BaseTest {

    // Base url
    private static final String PUBLIC_URL = "/users";

    @Autowired
    protected MockMvc mockMcv;

    @Test
    public void invalidBody() throws Exception {

        ResultActions result = this.mockMcv.perform(
                post(PUBLIC_URL)
        .contentType(MediaType.APPLICATION_JSON));

        result.andExpect(status().isBadRequest());
        result.andExpect(jsonPath("$.errors[0]", is("Required request body is missing or invalid")));
        result.andDo(print());
    }

    @Test
    public void emptyBody() throws Exception {
        ResultActions result = this.mockMcv.perform(
                post(PUBLIC_URL)
                .contentType(MediaType.APPLICATION_PDF)
                .content("{}"));

        result.andExpect(status().isInternalServerError());
        result.andExpect(jsonPath("$.errors[0]", is("An error occurred while processing your request")));
        result.andDo(print());
    }
}
