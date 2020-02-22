package com.example.dibchallenge.beer;

import java.util.List;

public interface BeerStrategy {

    BeerModel getRandomBeer();

    List<BeerModel> getRandomBeers(int count);

}
