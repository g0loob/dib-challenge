package com.example.dibchallenge.beer.punk_api_lib;

import java.util.List;

public class PunkApiBeerIngredientsDto {

    private List<PunkApiBeerNameAndAmmountWrapperDto> malt;
    private List<PunkApiBeerIngredientsHopsDto> hops;
    private String yeast;

    public PunkApiBeerIngredientsDto() {
    }

    public List<PunkApiBeerNameAndAmmountWrapperDto> getMalt() {
        return malt;
    }

    public void setMalt(List<PunkApiBeerNameAndAmmountWrapperDto> malt) {
        this.malt = malt;
    }

    public List<PunkApiBeerIngredientsHopsDto> getHops() {
        return hops;
    }

    public void setHops(List<PunkApiBeerIngredientsHopsDto> hops) {
        this.hops = hops;
    }

    public String getYeast() {
        return yeast;
    }

    public void setYeast(String yeast) {
        this.yeast = yeast;
    }
}
