package com.example.dibchallenge.punk_api_lib;

import com.example.dibchallenge.beer.strategy.BeerModel;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

public class PunkApiBeerDto implements BeerModel {

    private Long id;
    private String name;
    private String tagline;
    @JsonProperty("first_brewed") private String firstBrewed;
    private String description;
    @JsonProperty("image_url") private String imageUrl;
    private BigDecimal abv;
    private BigDecimal ibu;
    @JsonProperty("target_fg") private BigDecimal targetFg;
    @JsonProperty("target_og") private BigDecimal targetOg;
    private BigDecimal ebc;
    private BigDecimal srm;
    private BigDecimal ph;
    @JsonProperty("attenuation_level") private BigDecimal attenuationLevel;
    private PunkApiBeerUnitAndValueDto volume;
    @JsonProperty("boil_volume") private PunkApiBeerUnitAndValueDto boilVolume;
    private PunkApiBeerMethodDto method;
    private PunkApiBeerIngredientsDto ingredients;
    @JsonProperty("food_pairing") private List<String> foodPairing;
    @JsonProperty("brewers_tips") private String brewersTips;
    @JsonProperty("contributed_by") private String contributedBy;

    public PunkApiBeerDto() {
    }

    @Override
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTagline() {
        return tagline;
    }

    public void setTagline(String tagline) {
        this.tagline = tagline;
    }

    public String getFirstBrewed() {
        return firstBrewed;
    }

    public void setFirstBrewed(String firstBrewed) {
        this.firstBrewed = firstBrewed;
    }

    @Override
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public BigDecimal getAbv() {
        return abv;
    }

    public void setAbv(BigDecimal abv) {
        this.abv = abv;
    }

    public BigDecimal getIbu() {
        return ibu;
    }

    public void setIbu(BigDecimal ibu) {
        this.ibu = ibu;
    }

    public BigDecimal getTargetFg() {
        return targetFg;
    }

    public void setTargetFg(BigDecimal targetFg) {
        this.targetFg = targetFg;
    }

    public BigDecimal getTargetOg() {
        return targetOg;
    }

    public void setTargetOg(BigDecimal targetOg) {
        this.targetOg = targetOg;
    }

    public BigDecimal getEbc() {
        return ebc;
    }

    public void setEbc(BigDecimal ebc) {
        this.ebc = ebc;
    }

    public BigDecimal getSrm() {
        return srm;
    }

    public void setSrm(BigDecimal srm) {
        this.srm = srm;
    }

    public BigDecimal getPh() {
        return ph;
    }

    public void setPh(BigDecimal ph) {
        this.ph = ph;
    }

    public BigDecimal getAttenuationLevel() {
        return attenuationLevel;
    }

    public void setAttenuationLevel(BigDecimal attenuationLevel) {
        this.attenuationLevel = attenuationLevel;
    }

    public PunkApiBeerUnitAndValueDto getVolume() {
        return volume;
    }

    public void setVolume(PunkApiBeerUnitAndValueDto volume) {
        this.volume = volume;
    }

    public PunkApiBeerUnitAndValueDto getBoilVolume() {
        return boilVolume;
    }

    public void setBoilVolume(PunkApiBeerUnitAndValueDto boilVolume) {
        this.boilVolume = boilVolume;
    }

    public PunkApiBeerMethodDto getMethod() {
        return method;
    }

    public void setMethod(PunkApiBeerMethodDto method) {
        this.method = method;
    }

    public PunkApiBeerIngredientsDto getIngredients() {
        return ingredients;
    }

    public void setIngredients(PunkApiBeerIngredientsDto ingredients) {
        this.ingredients = ingredients;
    }

    public List<String> getFoodPairing() {
        return foodPairing;
    }

    public void setFoodPairing(List<String> foodPairing) {
        this.foodPairing = foodPairing;
    }

    public String getBrewersTips() {
        return brewersTips;
    }

    public void setBrewersTips(String brewersTips) {
        this.brewersTips = brewersTips;
    }

    public String getContributedBy() {
        return contributedBy;
    }

    public void setContributedBy(String contributedBy) {
        this.contributedBy = contributedBy;
    }

    @Override
    public List<BigDecimal> getMashTemperaturesInCelsius() {
        // TODO: defend from NPE
        return getMethod()
                .getMashTemps()
                .stream().map(mp -> mp.getTemp().getValue())
                .collect(Collectors.toList());
    }
}
