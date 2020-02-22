package com.example.dibchallenge.beer;

import com.example.dibchallenge.beer.strategy.BeerModel;
import org.springframework.data.domain.Page;

public class BeerMapper {

    public static BeerDto toDto(BeerEntity beerEntity) {
        return new BeerDto.Builder()
                .setId(beerEntity.getId())
                .setName(beerEntity.getName())
                .setDescription(beerEntity.getDescription())
                .setMeanTemperature(beerEntity.getMeanTemperature())
                .build();
    }

    public static Page<BeerDto> toPageDto(Page<BeerEntity> beerEntities) {
        return beerEntities.map(BeerMapper::toDto);
    }

    public static BeerEntity toEntity(BeerModel beerModel) {
        return new BeerEntity.Builder()
                .setExternalId(beerModel.getId())
                .setName(beerModel.getName())
                .setDescription(beerModel.getDescription())
                .build();
    }
}
