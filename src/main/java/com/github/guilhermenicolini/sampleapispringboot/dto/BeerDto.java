package com.github.guilhermenicolini.sampleapispringboot.dto;

import com.github.guilhermenicolini.sampleapispringboot.controllers.BeerController;
import com.github.guilhermenicolini.sampleapispringboot.domain.Beer;
import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.hateoas.RepresentationModel;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Objects;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;

@Schema(name = "Beer", description = "Beer diary")
public class BeerDto extends RepresentationModel<BeerDto> {

    @Schema
    private String id;

    @Schema(required = true)
    @NotBlank(message = "Name is required")
    private String beerName;

    @Schema(required = true)
    @NotBlank(message = "Brewer is required")
    private String brewer;

    @Schema(required = true)
    @NotNull(message = "Price is required")
    @Min(value = 0, message = "Price must be positive")
    private Double price;

    @Schema(required = true)
    @NotNull(message = "Sampled is required")
    private Long sampled;

    @Schema(required = true)
    @NotNull(message = "Rating is required")
    @Min(value = 1, message = "Rating must be between 1 and 5")
    @Max(value = 5, message = "Rating must be between 1 and 5")
    private Integer rating;

    @Schema
    private String notes;

    @Schema(required = true)
    @NotNull(message = "Abv is required")
    @Min(value = 0, message = "Abv must be positive")
    private Double abv;

    @Schema(required = true)
    @NotNull(message = "Ibu is required")
    @Min(value = 0, message = "Ibu must be positive")
    private Integer ibu;

    @Schema(required = true)
    @NotBlank(message = "Serving type is required")
    private String servingType;

    @Schema
    private FlavorWheelDto flavorWheel;

    public BeerDto() {
        price = 0D;
        sampled = 0L;
        rating = 0;
        abv = 0D;
        ibu = 0;
    }

    public BeerDto(Beer beer) {
        id = beer.getId();
        beerName = beer.getBeerName();
        brewer = beer.getBrewer();
        price = beer.getPrice();
        sampled = beer.getSampled().getTime();
        rating = beer.getRating();
        notes = beer.getNotes();
        abv = beer.getAbv();
        ibu = beer.getIbu();
        servingType = beer.getServingType();
        flavorWheel = new FlavorWheelDto(beer);

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

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Long getSampled() {
        return sampled;
    }

    public void setSampled(Long sampled) {
        this.sampled = sampled;
    }

    public Integer getRating() {
        return rating;
    }

    public void setRating(Integer rating) {
        this.rating = rating;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public Double getAbv() {
        return abv;
    }

    public void setAbv(Double abv) {
        this.abv = abv;
    }

    public Integer getIbu() {
        return ibu;
    }

    public void setIbu(Integer ibu) {
        this.ibu = ibu;
    }

    public String getServingType() {
        return servingType;
    }

    public void setServingType(String servingType) {
        this.servingType = servingType;
    }

    public FlavorWheelDto getFlavorWheel() {
        return flavorWheel;
    }

    public void setFlavorWheel(FlavorWheelDto flavorWheel) {
        this.flavorWheel = flavorWheel;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        BeerDto that = (BeerDto) o;
        return id.equals(that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), id);
    }
}