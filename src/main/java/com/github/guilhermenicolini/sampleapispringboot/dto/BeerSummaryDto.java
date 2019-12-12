package com.github.guilhermenicolini.sampleapispringboot.dto;

import com.github.guilhermenicolini.sampleapispringboot.controllers.BeerController;
import com.github.guilhermenicolini.sampleapispringboot.domain.Beer;
import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.hateoas.RepresentationModel;

import java.util.Objects;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;

@Schema(name = "BeerSummary", description = "Beer Summary")
public class BeerSummaryDto extends RepresentationModel<BeerSummaryDto> {

    @Schema
    private String id;

    @Schema
    private String beerName;

    @Schema
    private String brewer;

    @Schema
    private Integer rating;

    public BeerSummaryDto() {}

    public BeerSummaryDto(Beer beer) {
        setId(beer.getId());
        setBeerName(beer.getBeerName());
        setBrewer(beer.getBrewer());
        setRating(beer.getRating());

        add(linkTo(BeerController.class).slash(id).withSelfRel());
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getBeerName() {
        return beerName;
    }

    public void setBeerName(String beerName) {
        this.beerName = beerName;
    }

    public String getBrewer() {
        return brewer;
    }

    public void setBrewer(String brewer) {
        this.brewer = brewer;
    }

    public Integer getRating() {
        return rating;
    }

    public void setRating(Integer rating) {
        this.rating = rating;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        BeerSummaryDto that = (BeerSummaryDto) o;
        return id.equals(that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), id);
    }
}