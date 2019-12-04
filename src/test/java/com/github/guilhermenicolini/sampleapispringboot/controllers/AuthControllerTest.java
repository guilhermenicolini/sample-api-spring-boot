package com.github.guilhermenicolini.sampleapispringboot.controllers;

import com.github.guilhermenicolini.sampleapispringboot.BaseTest;
import com.github.guilhermenicolini.sampleapispringboot.Objects;
import com.github.guilhermenicolini.sampleapispringboot.business.AuthBusiness;
import com.github.guilhermenicolini.sampleapispringboot.exceptions.UnauthorizedException;
import com.github.guilhermenicolini.sampleapispringboot.security.JwtUtil;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

public class AuthControllerTest extends BaseTest {

    // Base url
    private static final String RESOURCE_URL = "/auth/login";

    // Constants
    private static final String ERROR_PATH = "$.errors";

    @Autowired
    protected MockMvc mockMcv;

    @MockBean
    private AuthBusiness authBusiness;

    @MockBean
    private JwtUtil jwtUtil;

    @Test
    public void createSuccess() throws Exception {

        String token = "Test";

        when(authBusiness.authenticate(any())).thenReturn(Objects.getUserDto());
        when(jwtUtil.generateToken(any(), any(), any())).thenReturn(token);

        ResultActions result = this.mockMcv.perform(
                post(RESOURCE_URL)
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"email\":\"john.doe@inbox.my\",\"password\":\"123456\"}"));

        result.andExpect(status().isNoContent());
        result.andExpect(header().string("Authorization", Matchers.is("Bearer " + token)));
        verify(authBusiness, times(1)).authenticate(any());
        verify(jwtUtil, times(1)).generateToken(any(), any(), any());
        result.andDo(print());
    }

    @Test
    public void requiredFields() throws Exception {

        when(authBusiness.authenticate(any())).thenReturn(null);
        when(jwtUtil.generateToken(any(), any(), any())).thenReturn(null);

        ResultActions result = this.mockMcv.perform(
                post(RESOURCE_URL)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{}"));

        result.andExpect(status().isBadRequest());
        verify(authBusiness, times(0)).authenticate(any());
        verify(jwtUtil, times(0)).generateToken(any(), any(), any());
        result.andExpect(jsonPath(ERROR_PATH, hasItem("Email is required")));
        result.andExpect(jsonPath(ERROR_PATH, hasItem("Password is required")));
        result.andDo(print());
    }

    @Test
    public void patternFields() throws Exception {

        when(authBusiness.authenticate(any())).thenReturn(null);
        when(jwtUtil.generateToken(any(), any(), any())).thenReturn(null);

        ResultActions result = this.mockMcv.perform(
                post(RESOURCE_URL)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"email\":\"john.doe\",\"password\":\"123456\"}"));

        result.andExpect(status().isBadRequest());
        verify(authBusiness, times(0)).authenticate(any());
        verify(jwtUtil, times(0)).generateToken(any(), any(), any());
        result.andExpect(jsonPath(ERROR_PATH, hasItem("Invalid email")));
        result.andDo(print());
    }

    @Test
    public void createError() throws Exception {

        when(jwtUtil.generateToken(any(), any(), any())).thenReturn(null);
        doThrow(new UnauthorizedException("Invalid login or password")).when(authBusiness).authenticate(any());

        ResultActions result = this.mockMcv.perform(
                post(RESOURCE_URL)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"email\":\"john.doe@inbox.my\",\"password\":\"123456\"}"));

        result.andExpect(status().isUnauthorized());
        verify(authBusiness, times(1)).authenticate(any());
        verify(jwtUtil, times(0)).generateToken(any(), any(), any());
        result.andExpect(jsonPath("$.errors[0]", is("Invalid login or password")));
        result.andDo(print());

    }
}
