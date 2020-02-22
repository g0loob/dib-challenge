package com.example.dibchallenge.beer.mash_temp;

import com.example.dibchallenge.beer.BeerEntity;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class BeerMashTemperatureServiceImpl implements BeerMashTemperatureService {

    @Override
    public List<BeerMashTemperatureEntity> createAll(final List<BigDecimal> temperaturesInCelsius, final BeerEntity beerEntity) {
        return temperaturesInCelsius
                .stream()
                .map(t -> new BeerMashTemperatureEntity(t, beerEntity))
                .collect(Collectors.toList());
    }

}
