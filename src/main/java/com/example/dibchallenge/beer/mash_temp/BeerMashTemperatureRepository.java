package com.example.dibchallenge.beer.mash_temp;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BeerMashTemperatureRepository extends JpaRepository<BeerMashTemperatureEntity, Long> {
}
