package com.github.guilhermenicolini.sampleapispringboot.services;

import com.github.guilhermenicolini.sampleapispringboot.BaseTest;
import com.github.guilhermenicolini.sampleapispringboot.Objects;
import com.github.guilhermenicolini.sampleapispringboot.domain.Beer;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class BeerServiceTest extends BaseTest {

    // Constants
    private static final String BEER_ID = "f4b5ba26-96a9-4175-b3cc-b0e4440a0d01";
    private static final String BEER_NAME = "Beer Name";
    private static final String BEER_BREWER = "Brewer";
    private static final String BEER_NOTES = "Notes";

    @Autowired
    private BeerService beerService;

    @Test
    public void getBeersByUserId() {

        List<Beer> beers = beerService.getBeersByUserId("ac1a8db7-3a2d-4bc8-a0a0-35eb537d8944");

        assertNotNull(beers);
        assertEquals(1, beers.size());
        assertEquals(BEER_ID, beers.get(0).getId());
        assertEquals(BEER_NAME, beers.get(0).getBeerName());
        assertEquals(BEER_BREWER, beers.get(0).getBrewer());
        assertEquals(12.9, beers.get(0).getPrice());
        assertEquals(1573776000000L, beers.get(0).getSampled().getTime());
        assertEquals(5, beers.get(0).getRating());
        assertEquals(BEER_NOTES, beers.get(0).getNotes());
        assertEquals(4.5, beers.get(0).getAbv());
        assertEquals(12, beers.get(0).getIbu());
        assertEquals("Cast", beers.get(0).getServingType());
        assertEquals(1, beers.get(0).getAlcohol());
        assertEquals(2, beers.get(0).getDarkFruit());
        assertEquals(3, beers.get(0).getCitrusFruit());
        assertEquals(4, beers.get(0).getHoppy());
        assertEquals(5, beers.get(0).getFloral());
        assertEquals(6, beers.get(0).getSpicy());
        assertEquals(7, beers.get(0).getHerbal());
        assertEquals(8, beers.get(0).getMalty());
        assertEquals(9, beers.get(0).getToffee());
        assertEquals(10, beers.get(0).getBurnt());
        assertEquals(11, beers.get(0).getSweet());
        assertEquals(12, beers.get(0).getSour());
        assertEquals(13, beers.get(0).getBitter());
        assertEquals(14, beers.get(0).getDry());
        assertEquals(15, beers.get(0).getBody());
        assertEquals(16, beers.get(0).getLinger());
    }

    @Test
    public void getBeersById() {

        Beer beer = beerService.getBeerById(BEER_ID);

        assertNotNull(beer);
        assertEquals(BEER_ID, beer.getId());
        assertEquals(BEER_NAME, beer.getBeerName());
        assertEquals(BEER_BREWER, beer.getBrewer());
        assertEquals(12.9, beer.getPrice());
        assertEquals(1573776000000L, beer.getSampled().getTime());
        assertEquals(5, beer.getRating());
        assertEquals(BEER_NOTES, beer.getNotes());
        assertEquals(4.5, beer.getAbv());
        assertEquals(12, beer.getIbu());
        assertEquals("Cast", beer.getServingType());
        assertEquals(1, beer.getAlcohol());
        assertEquals(2, beer.getDarkFruit());
        assertEquals(3, beer.getCitrusFruit());
        assertEquals(4, beer.getHoppy());
        assertEquals(5, beer.getFloral());
        assertEquals(6, beer.getSpicy());
        assertEquals(7, beer.getHerbal());
        assertEquals(8, beer.getMalty());
        assertEquals(9, beer.getToffee());
        assertEquals(10, beer.getBurnt());
        assertEquals(11, beer.getSweet());
        assertEquals(12, beer.getSour());
        assertEquals(13, beer.getBitter());
        assertEquals(14, beer.getDry());
        assertEquals(15, beer.getBody());
        assertEquals(16, beer.getLinger());
    }

    @Test
    public void create() {

        Beer beer = beerService.create(Objects.getBeerNoUser());

        assertNotNull(beer);
        assertNotNull(beer.getId());
        assertEquals(BEER_NAME, beer.getBeerName());
        assertEquals(BEER_BREWER, beer.getBrewer());
        assertEquals(12.9, beer.getPrice());
        assertEquals(1573776000000L, beer.getSampled().getTime());
        assertEquals(5, beer.getRating());
        assertEquals(BEER_NOTES, beer.getNotes());
        assertEquals(4.5, beer.getAbv());
        assertEquals(12, beer.getIbu());
        assertEquals("Cast", beer.getServingType());
        assertEquals(1, beer.getAlcohol());
        assertEquals(2, beer.getDarkFruit());
        assertEquals(3, beer.getCitrusFruit());
        assertEquals(4, beer.getHoppy());
        assertEquals(5, beer.getFloral());
        assertEquals(6, beer.getSpicy());
        assertEquals(7, beer.getHerbal());
        assertEquals(8, beer.getMalty());
        assertEquals(9, beer.getToffee());
        assertEquals(10, beer.getBurnt());
        assertEquals(11, beer.getSweet());
        assertEquals(12, beer.getSour());
        assertEquals(13, beer.getBitter());
        assertEquals(14, beer.getDry());
        assertEquals(15, beer.getBody());
        assertEquals(16, beer.getLinger());
    }

    @Test
    public void UpdateDelete() {
        Beer newBeer = beerService.create(Objects.getBeer());
        beerService.update(Objects.getBeerUpdate(newBeer.getId()));
        Beer beer = beerService.getBeerById(newBeer.getId());

        assertNotNull(beer);
        assertEquals(newBeer.getId(), beer.getId());
        assertEquals("Beer Name 2", beer.getBeerName());
        assertEquals("Brewer 2", beer.getBrewer());
        assertEquals(22.9, beer.getPrice());
        assertEquals(1563776000000L, beer.getSampled().getTime());
        assertEquals(1, beer.getRating());
        assertEquals("Notes 2", beer.getNotes());
        assertEquals(24.5, beer.getAbv());
        assertEquals(22, beer.getIbu());
        assertEquals("Cast 2", beer.getServingType());
        assertEquals(11, beer.getAlcohol());
        assertEquals(12, beer.getDarkFruit());
        assertEquals(13, beer.getCitrusFruit());
        assertEquals(14, beer.getHoppy());
        assertEquals(15, beer.getFloral());
        assertEquals(16, beer.getSpicy());
        assertEquals(17, beer.getHerbal());
        assertEquals(18, beer.getMalty());
        assertEquals(19, beer.getToffee());
        assertEquals(110, beer.getBurnt());
        assertEquals(111, beer.getSweet());
        assertEquals(112, beer.getSour());
        assertEquals(113, beer.getBitter());
        assertEquals(114, beer.getDry());
        assertEquals(115, beer.getBody());
        assertEquals(116, beer.getLinger());

        beerService.delete(newBeer.getId());
        beer = beerService.getBeerById(newBeer.getId());

        assertNull(beer);
    }
}
