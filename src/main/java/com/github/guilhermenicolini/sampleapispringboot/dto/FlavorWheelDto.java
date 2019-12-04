package com.github.guilhermenicolini.sampleapispringboot.dto;

import com.github.guilhermenicolini.sampleapispringboot.domain.Beer;
import io.swagger.v3.oas.annotations.media.Schema;

@Schema(name = "FlavorWheel", description = "Beer flavor wheel")
public class FlavorWheelDto {

    @Schema
    private Integer alcohol;

    @Schema
    private Integer darkFruit;

    @Schema
    private Integer citrusFruit;

    @Schema
    private Integer hoppy;

    @Schema
    private Integer floral;

    @Schema
    private Integer spicy;

    @Schema
    private Integer herbal;

    @Schema
    private Integer malty;

    @Schema
    private Integer toffee;

    @Schema
    private Integer burnt;

    @Schema
    private Integer sweet;

    @Schema
    private Integer sour;

    @Schema
    private Integer bitter;

    @Schema
    private Integer dry;

    @Schema
    private Integer body;

    @Schema
    private Integer linger;

    public FlavorWheelDto() { }

    public FlavorWheelDto(Beer beer) {
        setAlcohol(beer.getAlcohol());
        setDarkFruit(beer.getDarkFruit());
        setCitrusFruit(beer.getCitrusFruit());
        setHoppy(beer.getHoppy());
        setFloral(beer.getFloral());
        setSpicy(beer.getSpicy());
        setHerbal(beer.getHerbal());
        setMalty(beer.getMalty());
        setToffee(beer.getToffee());
        setBurnt(beer.getBurnt());
        setSweet(beer.getSweet());
        setSour(beer.getSour());
        setBitter(beer.getBitter());
        setDry(beer.getDry());
        setBody(beer.getBody());
        setLinger(beer.getLinger());
    }

    public Integer getAlcohol() {
        return alcohol;
    }

    public void setAlcohol(Integer alcohol) {
        this.alcohol = alcohol;
    }

    public Integer getDarkFruit() {
        return darkFruit;
    }

    public void setDarkFruit(Integer darkFruit) {
        this.darkFruit = darkFruit;
    }

    public Integer getCitrusFruit() {
        return citrusFruit;
    }

    public void setCitrusFruit(Integer citrusFruit) {
        this.citrusFruit = citrusFruit;
    }

    public Integer getHoppy() {
        return hoppy;
    }

    public void setHoppy(Integer hoppy) {
        this.hoppy = hoppy;
    }

    public Integer getFloral() {
        return floral;
    }

    public void setFloral(Integer floral) {
        this.floral = floral;
    }

    public Integer getSpicy() {
        return spicy;
    }

    public void setSpicy(Integer spicy) {
        this.spicy = spicy;
    }

    public Integer getHerbal() {
        return herbal;
    }

    public void setHerbal(Integer herbal) {
        this.herbal = herbal;
    }

    public Integer getMalty() {
        return malty;
    }

    public void setMalty(Integer malty) {
        this.malty = malty;
    }

    public Integer getToffee() {
        return toffee;
    }

    public void setToffee(Integer toffee) {
        this.toffee = toffee;
    }

    public Integer getBurnt() {
        return burnt;
    }

    public void setBurnt(Integer burnt) {
        this.burnt = burnt;
    }

    public Integer getSweet() {
        return sweet;
    }

    public void setSweet(Integer sweet) {
        this.sweet = sweet;
    }

    public Integer getSour() {
        return sour;
    }

    public void setSour(Integer sour) {
        this.sour = sour;
    }

    public Integer getBitter() {
        return bitter;
    }

    public void setBitter(Integer bitter) {
        this.bitter = bitter;
    }

    public Integer getDry() {
        return dry;
    }

    public void setDry(Integer dry) {
        this.dry = dry;
    }

    public Integer getBody() {
        return body;
    }

    public void setBody(Integer body) {
        this.body = body;
    }

    public Integer getLinger() {
        return linger;
    }

    public void setLinger(Integer linger) {
        this.linger = linger;
    }
}