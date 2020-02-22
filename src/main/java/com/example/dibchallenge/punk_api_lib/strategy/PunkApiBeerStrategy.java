package com.example.dibchallenge.punk_api_lib.strategy;

import com.example.dibchallenge.beer.strategy.BeerModel;
import com.example.dibchallenge.beer.strategy.BeerStrategy;
import com.example.dibchallenge.punk_api_lib.PunkApiBeerDto;
import com.example.dibchallenge.punk_api_lib.exception.ApiReturnedUnexpectedResultException;
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
        PunkApiBeerDto[] punkApiBeerDtos = restTemplate.getForObject(URL, PunkApiBeerDto[].class);
        if (punkApiBeerDtos != null && punkApiBeerDtos.length == 1) {
            return punkApiBeerDtos[0];
        } else {
            throw new ApiReturnedUnexpectedResultException();
        }
    }

    @Override
    public List<BeerModel> getRandomBeers(long count) {
        List<BeerModel> beers = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            beers.add(getRandomBeer());
        }
        return beers;
    }

}
