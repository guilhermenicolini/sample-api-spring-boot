package com.github.guilhermenicolini.sampleapispringboot.controllers;

import com.github.guilhermenicolini.sampleapispringboot.BaseTest;
import com.github.guilhermenicolini.sampleapispringboot.Objects;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class TokenTest extends BaseTest {

    private static final String PRIVATE_URL = "/beers";
    private static final String ERROR_TAG = "$.errors[0]";
    private static final String HEADER = "Authorization";
    private static final String BEARER = "Bearer ";
    private static final String INVALID_TOKEN = "Invalid token";


    @Autowired
    protected MockMvc mockMcv;

    @Test
    void missingToken() throws Exception {

        ResultActions result = this.mockMcv.perform(
                get(PRIVATE_URL)
                        .contentType(MediaType.APPLICATION_JSON));

        result.andExpect(status().isUnauthorized());
        result.andExpect(jsonPath(ERROR_TAG, is("Missing token for this request")));
        result.andDo(print());
    }

    @Test
    void wrongToken() throws Exception {

        ResultActions result = this.mockMcv.perform(
                get(PRIVATE_URL)
                        .header(HEADER, "Token " + Objects.WRONG_TOKEN)
                        .contentType(MediaType.APPLICATION_JSON));

        result.andExpect(status().isUnauthorized());
        result.andExpect(jsonPath(ERROR_TAG, is("Missing token for this request")));
        result.andDo(print());
    }

    @Test
    void invalidToken() throws Exception {

        ResultActions result = this.mockMcv.perform(
                get(PRIVATE_URL)
                        .header(HEADER, BEARER + Objects.WRONG_TOKEN)
                        .contentType(MediaType.APPLICATION_JSON));

        result.andExpect(status().isUnauthorized());
        result.andExpect(jsonPath(ERROR_TAG, is(INVALID_TOKEN)));
        result.andDo(print());
    }

    @Test
    void invalidTokenIssuer() throws Exception {

        ResultActions result = this.mockMcv.perform(
                get(PRIVATE_URL)
                        .header(HEADER, BEARER + Objects.INVALID_TOKEN)
                        .contentType(MediaType.APPLICATION_JSON));

        result.andExpect(status().isUnauthorized());
        result.andExpect(jsonPath(ERROR_TAG, is(INVALID_TOKEN)));
        result.andDo(print());
    }

    @Test
    void expiredTokenIssuer() throws Exception {

        ResultActions result = this.mockMcv.perform(
                get(PRIVATE_URL)
                        .header(HEADER, BEARER + Objects.EXPIRED_TOKEN)
                        .contentType(MediaType.APPLICATION_JSON));

        result.andExpect(status().isUnauthorized());
        result.andExpect(jsonPath(ERROR_TAG, is(INVALID_TOKEN)));
        result.andDo(print());
    }

    @Test
    void validToken() throws Exception {
        ResultActions result = this.mockMcv.perform(
                get(PRIVATE_URL)
                        .header(HEADER, BEARER + Objects.NO_EXPIRATION_TOKEN)
                        .contentType(MediaType.APPLICATION_JSON));

        result.andExpect(status().isOk());
        result.andDo(print());
    }
}
