package com.github.guilhermenicolini.sampleapispringboot.controllers;

import com.github.guilhermenicolini.sampleapispringboot.BaseTest;
import com.github.guilhermenicolini.sampleapispringboot.business.UserBusiness;
import com.github.guilhermenicolini.sampleapispringboot.security.JwtUtil;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import java.util.UUID;

import static org.hamcrest.Matchers.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

public class UserControllerTest extends BaseTest {

    // Base url
    private static final String RESOURCE_URL = "/users";

    private static final String ERROR_TAG = "$.errors";

    @Autowired
    protected MockMvc mockMcv;

    @MockBean
    private UserBusiness userBusiness;

    @MockBean
    private JwtUtil jwtUtil;

    @Test
    public void createSuccess() throws Exception {

        String id = UUID.randomUUID().toString();
        String token = "Test";

        when(userBusiness.signUp(any())).thenReturn(id);
        when(jwtUtil.generateToken(any(), any(), any())).thenReturn(token);

        ResultActions result = this.mockMcv.perform(
                post(RESOURCE_URL)
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"email\":\"jane.doe@inbox.my\",\"password\":\"123456\",\"name\":\"Jane Doe\"}"));

        result.andExpect(status().isCreated());
        result.andExpect(header().string("Location", Matchers.endsWith("users/" + id)));
        result.andExpect(header().string("Authorization", Matchers.is("Bearer " + token)));
        result.andExpect(header().string("access-control-expose-headers", Matchers.is("Authorization, Location")));
        verify(userBusiness, times(1)).signUp(any());
        verify(jwtUtil, times(1)).generateToken(any(), any(), any());
        result.andDo(print());
    }

    @Test
    public void requiredFields() throws Exception {

        when(userBusiness.signUp(any())).thenReturn(null);
        when(jwtUtil.generateToken(any(), any(), any())).thenReturn(null);

        ResultActions result = this.mockMcv.perform(
                post(RESOURCE_URL)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{}"));

        result.andExpect(status().isBadRequest());
        verify(userBusiness, times(0)).signUp(any());
        verify(jwtUtil, times(0)).generateToken(any(), any(), any());
        result.andExpect(jsonPath(ERROR_TAG, hasItem("Email is required")));
        result.andExpect(jsonPath(ERROR_TAG, hasItem("Password is required")));
        result.andExpect(jsonPath(ERROR_TAG, hasItem("Name is required")));
        result.andDo(print());
    }

    @Test
    public void patternFields() throws Exception {

        when(userBusiness.signUp(any())).thenReturn(null);
        when(jwtUtil.generateToken(any(), any(), any())).thenReturn(null);

        ResultActions result = this.mockMcv.perform(
                post(RESOURCE_URL)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"email\":\"jane.doe\",\"password\":\"123456\",\"name\":\"Jane Doe Neque porro quisquam est qui dolorem ipsum quia dolor sit amet, consectetur, adipisci velit...\"}"));

        result.andExpect(status().isBadRequest());
        verify(userBusiness, times(0)).signUp(any());
        verify(jwtUtil, times(0)).generateToken(any(), any(), any());
        result.andExpect(jsonPath(ERROR_TAG, hasItem("Invalid email")));
        result.andExpect(jsonPath(ERROR_TAG, hasItem("Name must be less than 100 characters")));
        result.andDo(print());
    }
}
