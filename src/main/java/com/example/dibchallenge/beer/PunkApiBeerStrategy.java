package com.example.dibchallenge.beer;

import com.example.dibchallenge.beer.punk_api_lib.PunkApiBeerDto;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

public class PunkApiBeerStrategy implements BeerStrategy {

    private static final String URL = "https://api.punkapi.com/v2/beers/random";

    private RestTemplate restTemplate = new RestTemplate();

    public PunkApiBeerStrategy() {
    }

    @Override
    public BeerModel getRandomBeer() {
        return restTemplate.getForObject(URL, PunkApiBeerDto.class);
    }

    @Override
    public List<BeerModel> getRandomBeers(int count) {
        List<BeerModel> beers = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            beers.add(getRandomBeer());
        }
        return beers;
    }

}
