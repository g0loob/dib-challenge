package com.example.dibchallenge.beer.mash_temp;

import com.example.dibchallenge.beer.BeerEntity;

import java.math.BigDecimal;
import java.util.List;

public interface BeerMashTemperatureService {

    List<BeerMashTemperatureEntity> createAll(final List<BigDecimal> temperaturesInCelsius, final BeerEntity beerEntity);

}
