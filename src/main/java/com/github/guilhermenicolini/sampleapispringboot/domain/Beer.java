package com.github.guilhermenicolini.sampleapispringboot.domain;

import com.github.guilhermenicolini.sampleapispringboot.dto.BeerDto;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "diary")
public class Beer {

    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String id;

    private String beerName;
    private String brewer;
    private Double price;
    private Timestamp sampled;
    private Integer rating;
    private String notes;

    private Double abv;
    private Integer ibu;

    private String servingType;

    private Integer alcohol;
    private Integer darkFruit;
    private Integer citrusFruit;
    private Integer hoppy;
    private Integer floral;
    private Integer spicy;
    private Integer herbal;
    private Integer malty;
    private Integer toffee;
    private Integer burnt;
    private Integer sweet;
    private Integer sour;
    private Integer bitter;
    private Integer dry;
    private Integer body;
    private Integer linger;

    @ManyToOne
    @JoinColumn(name = "userId")
    private User user;

    public Beer() {}

    public Beer(BeerDto dto) {
        updateData(dto);
    }

    public void updateData(BeerDto dto) {
        id = dto.getId();
        beerName = dto.getBeerName();
        brewer = dto.getBrewer();
        price = dto.getPrice();
        sampled = new Timestamp(dto.getSampled());
        rating = dto.getRating();
        notes = dto.getNotes();
        abv = dto.getAbv();
        ibu = dto.getIbu();
        servingType = dto.getServingType();

        if (dto.getFlavorWheel() != null) {
            alcohol = dto.getFlavorWheel().getAlcohol();
            darkFruit = dto.getFlavorWheel().getDarkFruit();
            citrusFruit = dto.getFlavorWheel().getCitrusFruit();
            hoppy = dto.getFlavorWheel().getHoppy();
            floral = dto.getFlavorWheel().getFloral();
            spicy = dto.getFlavorWheel().getSpicy();
            herbal = dto.getFlavorWheel().getHerbal();
            malty = dto.getFlavorWheel().getMalty();
            toffee = dto.getFlavorWheel().getToffee();
            burnt = dto.getFlavorWheel().getBurnt();
            sweet = dto.getFlavorWheel().getSweet();
            sour = dto.getFlavorWheel().getSour();
            bitter = dto.getFlavorWheel().getBitter();
            dry = dto.getFlavorWheel().getDry();
            body = dto.getFlavorWheel().getBody();
            linger = dto.getFlavorWheel().getLinger();
        }
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

    public Timestamp getSampled() {
        return sampled;
    }

    public void setSampled(Timestamp sampled) {
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

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}