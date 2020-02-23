package com.example.dibchallenge.beer;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface BeerService {

    Page<BeerEntity> getAll(Pageable pageable);

    BeerEntity getById(Long id);

    void deleteById(Long id);

    Long insertBeersFromPunkApi(Long count);

}
