package com.example.dibchallenge.beer;

import com.example.dibchallenge.BeerStrategyRegistry;
import com.example.dibchallenge.beer.exception.BeerNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class BeerServiceImpl implements BeerService {

    private final BeerStrategyRegistry beerStrategyRegistry;
    private final BeerRepository beerRepository;

    @Autowired
    public BeerServiceImpl(BeerStrategyRegistry beerStrategyRegistry, BeerRepository beerRepository) {
        this.beerStrategyRegistry = beerStrategyRegistry;
        this.beerRepository = beerRepository;
    }

    @Override
    public Page<BeerDto> getAll(Pageable pageable) {
        return BeerMapper.toPageDto(beerRepository.findAll(pageable));
    }

    @Override
    public BeerDto getById(Long id) {
        BeerEntity beerEntity = beerRepository.findById(id)
                .orElseThrow(() -> new BeerNotFoundException("Beer not found!"));
        return BeerMapper.toDto(beerEntity);
    }

    @Override
    public void deleteById(Long id) {
        beerRepository.deleteById(id);
    }

    @Override
    public Long insertBeers(Long count) {
        return null;
    }

}
