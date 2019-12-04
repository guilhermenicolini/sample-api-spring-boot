package com.github.guilhermenicolini.sampleapispringboot.controllers;

import com.github.guilhermenicolini.sampleapispringboot.BaseTest;
import com.github.guilhermenicolini.sampleapispringboot.Objects;
import com.github.guilhermenicolini.sampleapispringboot.business.BeerBusiness;
import com.github.guilhermenicolini.sampleapispringboot.exceptions.BusinessException;
import com.github.guilhermenicolini.sampleapispringboot.exceptions.ForbiddenException;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import java.util.UUID;

import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

public class BeerControllerTest extends BaseTest {

    // Base url
    private static final String RESOURCE_URL = "/beers";

    @Autowired
    protected MockMvc mockMcv;

    @MockBean
    private BeerBusiness beerBusiness;

    @Test
    @WithMockUser(username = "john.doe@inbox.my")
    public void getAllBeers() throws Exception {

        when(beerBusiness.getAll()).thenReturn(Objects.getBeersSummary());

        ResultActions result = this.mockMcv.perform(
                get(RESOURCE_URL)
                .contentType(MediaType.APPLICATION_JSON));

        result.andExpect(status().isOk());
        result.andExpect(jsonPath("$.[0].id", is("f4b5ba26-96a9-4175-b3cc-b0e4440a0d01")));
        result.andExpect(jsonPath("$.[0].beerName", is("Beer Name")));
        result.andExpect(jsonPath("$.[0].brewer", is("Brewer")));
        result.andExpect(jsonPath("$.[0].hating", is(5)));
        verify(beerBusiness, times(1)).getAll();
        result.andDo(print());
    }

    @Test
    @WithMockUser(username = "john.doe@inbox.my")
    public void getBeerById() throws Exception {

        when(beerBusiness.get(any())).thenReturn(Objects.getBeerDto());

        ResultActions result = this.mockMcv.perform(
                get(RESOURCE_URL + "/id"));

        result.andExpect(status().isOk());
        result.andExpect(jsonPath("$.id", is("f4b5ba26-96a9-4175-b3cc-b0e4440a0d01")));
        result.andExpect(jsonPath("$.beerName", is("Beer Name")));
        result.andExpect(jsonPath("$.brewer", is("Brewer")));
        result.andExpect(jsonPath("$.price", is(12.9)));
        result.andExpect(jsonPath("$.sampled", is(1573776000000L)));
        result.andExpect(jsonPath("$.hating", is(5)));
        result.andExpect(jsonPath("$.notes", is("Notes")));
        result.andExpect(jsonPath("$.abv", is(4.5)));
        result.andExpect(jsonPath("$.ibu", is(12)));
        result.andExpect(jsonPath("$.servingType", is("Cast")));
        result.andExpect(jsonPath("$.flavorWheel.alcohol", is(1)));
        result.andExpect(jsonPath("$.flavorWheel.darkFruit", is(2)));
        result.andExpect(jsonPath("$.flavorWheel.citrusFruit", is(3)));
        result.andExpect(jsonPath("$.flavorWheel.hoppy", is(4)));
        result.andExpect(jsonPath("$.flavorWheel.floral", is(5)));
        result.andExpect(jsonPath("$.flavorWheel.spicy", is(6)));
        result.andExpect(jsonPath("$.flavorWheel.herbal", is(7)));
        result.andExpect(jsonPath("$.flavorWheel.malty", is(8)));
        result.andExpect(jsonPath("$.flavorWheel.toffee", is(9)));
        result.andExpect(jsonPath("$.flavorWheel.burnt", is(10)));
        result.andExpect(jsonPath("$.flavorWheel.sweet", is(11)));
        result.andExpect(jsonPath("$.flavorWheel.sour", is(12)));
        result.andExpect(jsonPath("$.flavorWheel.bitter", is(13)));
        result.andExpect(jsonPath("$.flavorWheel.dry", is(14)));
        result.andExpect(jsonPath("$.flavorWheel.body", is(15)));
        result.andExpect(jsonPath("$.flavorWheel.linger", is(16)));
        verify(beerBusiness, times(1)).get(any());
        result.andDo(print());
    }

    @Test
    @WithMockUser(username = "john.doe@inbox.my")
    public void forbidden() throws Exception {
        doThrow(new ForbiddenException("Forbidden")).when(beerBusiness).get(any());

        ResultActions result = this.mockMcv.perform(
                get(RESOURCE_URL + "/id"));

        result.andExpect(status().isForbidden());
        verify(beerBusiness, times(1)).get(any());
        result.andExpect(jsonPath("$.errors[0]", is("Forbidden")));
        result.andDo(print());
    }

    @Test
    @WithMockUser(username = "john.doe@inbox.my")
    public void notfound() throws Exception {
        doThrow(new BusinessException("Not found")).when(beerBusiness).get(any());

        ResultActions result = this.mockMcv.perform(
                get(RESOURCE_URL + "/id"));

        result.andExpect(status().isBadRequest());
        verify(beerBusiness, times(1)).get(any());
        result.andExpect(jsonPath("$.errors[0]", is("Not found")));
        result.andDo(print());
    }

    @Test
    @WithMockUser(username = "john.doe@inbox.my")
    public void createSuccess() throws Exception {

        String id = UUID.randomUUID().toString();

        when(beerBusiness.create(any())).thenReturn(id);

        ResultActions result = this.mockMcv.perform(
                post(RESOURCE_URL)
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"beerName\":\"Beer Name\",\"brewer\":\"Brewer\",\"price\":12.9,\"sampled\":1573782592788,\"hating\":5,\"abv\":4.5,\"ibu\":12,\"servingType\":\"Cast\",\"notes\":\"Notes\"}"));

        result.andExpect(status().isCreated());
        result.andExpect(header().string("Location", Matchers.endsWith("beers/" + id)));
        result.andExpect(header().string("access-control-expose-headers", Matchers.is("Location")));
        verify(beerBusiness, times(1)).create(any());
        result.andDo(print());
    }

    @Test
    @WithMockUser(username = "john.doe@inbox.my")
    public void updateSuccess() throws Exception {

        String id = UUID.randomUUID().toString();

        doNothing().when(beerBusiness).update(any());

        ResultActions result = this.mockMcv.perform(
                put(RESOURCE_URL + "/" + id)
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"beerName\":\"Beer Name\",\"brewer\":\"Brewer\",\"price\":12.9,\"sampled\":1573782592788,\"hating\":5,\"abv\":4.5,\"ibu\":12,\"servingType\":\"Cast\"}"));

        result.andExpect(status().isNoContent());
        verify(beerBusiness, times(1)).update(any());
        result.andDo(print());
    }

    @Test
    @WithMockUser(username = "john.doe@inbox.my")
    public void deleteSuccess() throws Exception {

        String id = UUID.randomUUID().toString();

        doNothing().when(beerBusiness).delete(any());

        ResultActions result = this.mockMcv.perform(
                delete(RESOURCE_URL + "/" + id));

        result.andExpect(status().isNoContent());
        verify(beerBusiness, times(1)).delete(any());
        result.andDo(print());
    }
}
