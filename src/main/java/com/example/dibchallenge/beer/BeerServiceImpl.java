package com.example.dibchallenge.beer;

import com.example.dibchallenge.beer.exception.BeerNotFoundException;
import com.example.dibchallenge.beer.mash_temp.BeerMashTemperatureEntity;
import com.example.dibchallenge.beer.mash_temp.BeerMashTemperatureService;
import com.example.dibchallenge.beer.strategy.BeerModel;
import com.example.dibchallenge.beer.strategy.BeerStrategy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class BeerServiceImpl implements BeerService {

    @Value("${max_number_of_beers_to_insert_in_db}")
    private Long maxNumberOfBeersToInsertInDb;

    private final BeerRepository beerRepository;
    private final BeerMashTemperatureService beerMashTemperatureService;
    private final BeerStrategy punkApiBeerStrategy;

    @Autowired
    public BeerServiceImpl(
            @Qualifier("punkApiBeerStrategy") BeerStrategy beerStrategy,
            BeerRepository beerRepository,
            BeerMashTemperatureService beerMashTemperatureService) {
        this.beerRepository = beerRepository;
        this.punkApiBeerStrategy = beerStrategy;
        this.beerMashTemperatureService = beerMashTemperatureService;
    }

    @Override
    @Transactional(readOnly = true)
    public Page<BeerEntity> getAll(Pageable pageable) {
        return beerRepository.findAll(pageable);
    }

    @Override
    @Transactional(readOnly = true)
    public BeerEntity getById(Long id) {
        return beerRepository.findById(id)
                .orElseThrow(() -> new BeerNotFoundException("Beer not found!"));
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        if (beerRepository.existsById(id)) {
            beerRepository.deleteById(id);
        }
    }

    @Override
    @Transactional
    public Long insertBeers(Long count) {
        long numberOfBeersInDb = beerRepository.count();
        long numberOfBeersToStoreInDB = maxNumberOfBeersToInsertInDb - numberOfBeersInDb;
        long numberOfBeersSavedInDB = numberOfBeersToStoreInDB;

        while (numberOfBeersToStoreInDB > 0L) {
            Map<Long, BeerModel> randomBeers = getRandomBeers(numberOfBeersToStoreInDB); // TODO: better method name for this return type
            numberOfBeersToStoreInDB -= randomBeers.keySet().size();

            List<BeerEntity> beerEntitiesToSave = new ArrayList<>();
            for (BeerModel beerModel : randomBeers.values()) {
                BeerEntity beerEntity = BeerMapper.toEntity(beerModel);
                List<BeerMashTemperatureEntity> beerMashTemperatureEntities = beerMashTemperatureService
                        .createAll(beerModel.getMashTemperaturesInCelsius(), beerEntity);
                beerEntity.setTemperatures(beerMashTemperatureEntities);
                beerEntitiesToSave.add(beerEntity);
            }
            beerRepository.saveAll(beerEntitiesToSave); // TODO: how to enable/get batch insert
        }

        return numberOfBeersSavedInDB;
    }

    private Map<Long, BeerModel> getRandomBeers(long count) {
        Map<Long, BeerModel> randomBeers = punkApiBeerStrategy.getRandomBeers(count)
                .stream()
                .collect(Collectors.toMap(BeerModel::getId, b -> b, (b1, b2) -> b1));
        Set<Long> beerIdsThatAreInDB = beerRepository.findBeerIdsThatAreInDB(randomBeers.keySet());
        randomBeers.keySet().removeAll(beerIdsThatAreInDB);
        return randomBeers;
    }

}
