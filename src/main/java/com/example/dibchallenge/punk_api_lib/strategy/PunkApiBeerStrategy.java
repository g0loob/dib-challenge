package com.example.dibchallenge.punk_api_lib.strategy;

import com.example.dibchallenge.beer.strategy.BeerModel;
import com.example.dibchallenge.beer.strategy.BeerStrategy;
import com.example.dibchallenge.punk_api_lib.PunkApiBeerDto;
import com.example.dibchallenge.punk_api_lib.PunkApiRestTemplateResponseErrorHandler;
import com.example.dibchallenge.punk_api_lib.exception.ApiReturnedUnexpectedResultException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Component
public class PunkApiBeerStrategy implements BeerStrategy {

    private static final String URL = "https://api.punkapi.com/v2/beers/random";
    private static HttpEntity<PunkApiBeerDto[]> HTTP_ENTITY;

    static {
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        headers.add("user-agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/54.0.2840.99 Safari/537.36");
        HTTP_ENTITY = new HttpEntity<>(headers);
    }

    private RestTemplate restTemplate;

    @Autowired
    public PunkApiBeerStrategy(RestTemplateBuilder restTemplateBuilder) {
        restTemplate = restTemplateBuilder
                .errorHandler(new PunkApiRestTemplateResponseErrorHandler())
                .build();
    }

    @Override
    public BeerModel getRandomBeer() {
        ResponseEntity<PunkApiBeerDto[]> response = restTemplate.exchange(URL, HttpMethod.GET, HTTP_ENTITY, PunkApiBeerDto[].class);

        PunkApiBeerDto[] punkApiBeerDtos = response.getBody();
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
