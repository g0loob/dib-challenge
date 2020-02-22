package com.example.dibchallenge.beer.strategy;

import java.util.List;

public interface BeerStrategy {

    BeerModel getRandomBeer();

    List<BeerModel> getRandomBeers(long count);

}
