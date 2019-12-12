package com.github.guilhermenicolini.sampleapispringboot.business;

import com.github.guilhermenicolini.sampleapispringboot.BaseTest;
import com.github.guilhermenicolini.sampleapispringboot.Objects;
import com.github.guilhermenicolini.sampleapispringboot.dto.BeerDto;
import com.github.guilhermenicolini.sampleapispringboot.dto.BeerSummaryDto;
import com.github.guilhermenicolini.sampleapispringboot.exceptions.BusinessException;
import com.github.guilhermenicolini.sampleapispringboot.exceptions.ForbiddenException;
import com.github.guilhermenicolini.sampleapispringboot.services.BeerService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class BeerBusinessTest extends BaseTest {

    // Constants
    private static final String USER_ID = "f4b5ba26-96a9-4175-b3cc-b0e4440a0d01";

    @MockBean
    private BeerService beerService;

    @Autowired
    private BeerBusiness beerBusiness;

    @BeforeEach
    public void setContext() {
        SecurityContextHolder.getContext().setAuthentication(Objects.getAuth());
    }

    @Test
    public void getAll() {

        when(beerService.getBeersByUserId(any())).thenReturn(Objects.getBeers());

        List<BeerSummaryDto> beers = beerBusiness.getAll();

        verify(beerService, times(1)).getBeersByUserId(any());
        assertNotNull(beers);
        assertEquals(1, beers.size());

        assertEquals(USER_ID, beers.get(0).getId());
        assertEquals("Beer Name", beers.get(0).getBeerName());
        assertEquals("Brewer", beers.get(0).getBrewer());
        assertEquals(5, beers.get(0).getRating());
    }

    @Test
    public void get() {

        when(beerService.getBeerById(any())).thenReturn(Objects.getBeer());

        BeerDto beer = beerBusiness.get(any());

        verify(beerService, times(1)).getBeerById(any());
        assertNotNull(beer);
        assertEquals(USER_ID, beer.getId());
        assertEquals("Beer Name", beer.getBeerName());
        assertEquals("Brewer", beer.getBrewer());
        assertEquals(12.9, beer.getPrice());
        assertEquals(1573776000000L, beer.getSampled());
        assertEquals(5, beer.getRating());
        assertEquals("Notes", beer.getNotes());
        assertEquals(4.5, beer.getAbv());
        assertEquals(12, beer.getIbu());
        assertEquals("Cast", beer.getServingType());
        assertEquals(1, beer.getFlavorWheel().getAlcohol());
        assertEquals(2, beer.getFlavorWheel().getDarkFruit());
        assertEquals(3, beer.getFlavorWheel().getCitrusFruit());
        assertEquals(4, beer.getFlavorWheel().getHoppy());
        assertEquals(5, beer.getFlavorWheel().getFloral());
        assertEquals(6, beer.getFlavorWheel().getSpicy());
        assertEquals(7, beer.getFlavorWheel().getHerbal());
        assertEquals(8, beer.getFlavorWheel().getMalty());
        assertEquals(9, beer.getFlavorWheel().getToffee());
        assertEquals(10, beer.getFlavorWheel().getBurnt());
        assertEquals(11, beer.getFlavorWheel().getSweet());
        assertEquals(12, beer.getFlavorWheel().getSour());
        assertEquals(13, beer.getFlavorWheel().getBitter());
        assertEquals(14, beer.getFlavorWheel().getDry());
        assertEquals(15, beer.getFlavorWheel().getBody());
        assertEquals(16, beer.getFlavorWheel().getLinger());
    }

    @Test()
    public void getWrong() {

        SecurityContextHolder.getContext().setAuthentication(Objects.getAuth2());

        when(beerService.getBeerById(any())).thenReturn(Objects.getBeer());

        assertThrows(ForbiddenException.class, () -> beerBusiness.get(any()));

        verify(beerService, times(1)).getBeerById(any());
    }

    @Test()
    public void getNotFound() {

        when(beerService.getBeerById(any())).thenReturn(null);

        assertThrows(BusinessException.class, () -> beerBusiness.get(any()));

        verify(beerService, times(1)).getBeerById(any());
    }

    @Test
    public void create() {

        when(beerService.create(any())).thenReturn(Objects.getBeer());

        String id = beerBusiness.create(Objects.getBeerDto());

        verify(beerService, times(1)).create(any());
        assertEquals(USER_ID, id);
    }

    @Test
    public void update() {

        when(beerService.getBeerById(any())).thenReturn(Objects.getBeer());
        doNothing().when(beerService).update(any());

        beerBusiness.update(Objects.getBeerDto());

        verify(beerService, times(1)).getBeerById(any());
        verify(beerService, times(1)).update(any());
    }

    @Test
    public void updateNoFlavor() {
        when(beerService.getBeerById(any())).thenReturn(Objects.getBeer());
        doNothing().when(beerService).update(any());

        BeerDto beer = Objects.getBeerDto();
        beer.setFlavorWheel(null);

        beerBusiness.update(beer);

        verify(beerService, times(1)).getBeerById(any());
        verify(beerService, times(1)).update(any());
    }

    @Test
    public void updateNotFound() {

        when(beerService.getBeerById(any())).thenReturn(null);
        doNothing().when(beerService).update(any());

        assertThrows(BusinessException.class, () -> beerBusiness.update(Objects.getBeerDto()));

        verify(beerService, times(1)).getBeerById(any());
        verify(beerService, times(0)).update(any());
    }

    @Test
    public void updateWrong() {

        SecurityContextHolder.getContext().setAuthentication(Objects.getAuth2());

        when(beerService.getBeerById(any())).thenReturn(Objects.getBeer());
        doNothing().when(beerService).update(any());

        assertThrows(ForbiddenException.class, () -> beerBusiness.update(Objects.getBeerDto()));

        verify(beerService, times(1)).getBeerById(any());
        verify(beerService, times(0)).update(any());
    }

    @Test
    public void delete() {

        when(beerService.getBeerById(any())).thenReturn(Objects.getBeer());
        doNothing().when(beerService).delete(any());

        beerBusiness.delete(USER_ID);

        verify(beerService, times(1)).getBeerById(any());
        verify(beerService, times(1)).delete(any());
    }

    @Test
    public void deleteNotFound() {

        when(beerService.getBeerById(any())).thenReturn(null);
        doNothing().when(beerService).delete(any());

        assertThrows(BusinessException.class, () -> beerBusiness.delete(USER_ID));

        verify(beerService, times(1)).getBeerById(any());
        verify(beerService, times(0)).delete(any());
    }

    @Test
    public void deleteWrong() {

        SecurityContextHolder.getContext().setAuthentication(Objects.getAuth2());

        when(beerService.getBeerById(any())).thenReturn(Objects.getBeer());
        doNothing().when(beerService).delete(any());

        assertThrows(ForbiddenException.class, () -> beerBusiness.delete(USER_ID));

        verify(beerService, times(1)).getBeerById(any());
        verify(beerService, times(0)).delete(any());
    }
}
