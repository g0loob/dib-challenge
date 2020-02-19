package com.example.dibchallenge.beer.punk_api_lib;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class PunkApiBeerMethodDto {

    @JsonProperty("mash_temp") private List<PunkApiBeerMethodMashTempDto> mashTemps;
    private PunkApiBeerTempWrapperDto fermentation;
    // ignore 'twist' property because I don't know its type

    public PunkApiBeerMethodDto() {
    }

    public List<PunkApiBeerMethodMashTempDto> getMashTemps() {
        return mashTemps;
    }

    public void setMashTemps(List<PunkApiBeerMethodMashTempDto> mashTemps) {
        this.mashTemps = mashTemps;
    }

    public PunkApiBeerTempWrapperDto getFermentation() {
        return fermentation;
    }

    public void setFermentation(PunkApiBeerTempWrapperDto fermentation) {
        this.fermentation = fermentation;
    }
}
