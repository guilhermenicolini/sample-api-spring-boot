package com.github.guilhermenicolini.sampleapispringboot.services;

import com.github.guilhermenicolini.sampleapispringboot.domain.Beer;
import com.github.guilhermenicolini.sampleapispringboot.domain.User;
import com.github.guilhermenicolini.sampleapispringboot.repositories.BeerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BeerService {

    @Autowired
    private BeerRepository beerRepository;

    public List<Beer> getBeersByUserId(String userId) {
        return beerRepository.findAllByUserOrderBySampledDesc(new User(userId));
    }

    public Beer getBeerById(String beerId) {
        return beerRepository.findById(beerId).orElse(null);
    }

    public Beer create(Beer beer) {
        beer.setId(null);
        return beerRepository.save(beer);
    }

    public void update(Beer beer) {
        beerRepository.save(beer);
    }

    public void delete(String beerId) {
        beerRepository.deleteById(beerId);
    }
}
