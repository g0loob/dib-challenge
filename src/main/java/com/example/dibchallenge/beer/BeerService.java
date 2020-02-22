package com.example.dibchallenge.beer;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface BeerService {

    Page<BeerDto> getAll(Pageable pageable);

    BeerDto getById(Long id);

    void deleteById(Long id);

    Long insertBeers(Long count);

}
