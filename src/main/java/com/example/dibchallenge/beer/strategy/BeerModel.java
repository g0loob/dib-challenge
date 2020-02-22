package com.example.dibchallenge.beer.strategy;

import java.math.BigDecimal;
import java.util.List;


public interface BeerModel {

    Long getId();

    String getName();

    String getDescription();

    List<BigDecimal> getMashTemperaturesInCelsius();

}
