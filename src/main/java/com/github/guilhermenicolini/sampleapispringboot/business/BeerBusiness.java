package com.github.guilhermenicolini.sampleapispringboot.business;

import com.github.guilhermenicolini.sampleapispringboot.domain.Beer;
import com.github.guilhermenicolini.sampleapispringboot.domain.User;
import com.github.guilhermenicolini.sampleapispringboot.dto.BeerDto;
import com.github.guilhermenicolini.sampleapispringboot.dto.BeerSummaryDto;
import com.github.guilhermenicolini.sampleapispringboot.exceptions.BusinessException;
import com.github.guilhermenicolini.sampleapispringboot.exceptions.ForbiddenException;
import com.github.guilhermenicolini.sampleapispringboot.security.UserContext;
import com.github.guilhermenicolini.sampleapispringboot.services.BeerService;
import com.github.guilhermenicolini.sampleapispringboot.services.UserService;
import com.github.guilhermenicolini.sampleapispringboot.utils.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class BeerBusiness {

    @Autowired
    private BeerService beerService;

    public List<BeerSummaryDto> getAll() {
        UserContext user = UserService.authenticated();
        return beerService.getBeersByUserId(user.getId())
                .stream()
                .map(BeerSummaryDto::new)
                .collect(Collectors.toList());
    }

    public BeerDto get(String beerId) {

        UserContext user = UserService.authenticated();
        Beer beer = beerService.getBeerById(beerId);

        if (beer == null) throw new BusinessException(Constants.MESSAGE_BEER_NOT_FOUND);

        if (!beer.getUser().getId().equals(user.getId()))
            throw new ForbiddenException(Constants.MESSAGE_FORBIDDEN);

        return new BeerDto(beer);
    }

    public String create(BeerDto payload) {

        UserContext user = UserService.authenticated();

        Beer beer = new Beer(payload);

        // add user to beer
        beer.setUser(new User(user.getId()));

        return beerService.create(beer).getId();
    }

    public void update(BeerDto payload) {

        UserContext user = UserService.authenticated();
        Beer beer = beerService.getBeerById(payload.getId());

        if (beer == null) throw new BusinessException("Beer not found");

        if (!beer.getUser().getId().equals(user.getId()))
            throw new ForbiddenException("You are not allowed access this resource");

        // update beer data
        beer.updateData(payload);

        beerService.update(beer);
    }

    public void delete(String beerId) {

        UserContext user = UserService.authenticated();
        Beer beer = beerService.getBeerById(beerId);

        if (beer == null) throw new BusinessException("Beer not found");

        if (!beer.getUser().getId().equals(user.getId()))
            throw new ForbiddenException("You are not allowed access this resource");

        // delete beer
        beerService.delete(beerId);
    }
}
