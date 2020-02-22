package com.example.dibchallenge.beer;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.Set;

@Repository
public interface BeerRepository extends JpaRepository<BeerEntity, Long> {

    @Override
    @EntityGraph(attributePaths = { "temperatures" })
    Page<BeerEntity> findAll(Pageable pageable);

    @Override
    @EntityGraph(attributePaths = { "temperatures" })
    Optional<BeerEntity> findById(Long aLong);

    @Query("select b.externalId from BeerEntity b where b.externalId in :randomBeerIds")
    Set<Long> findBeerIdsThatAreInDB(Set<Long> randomBeerIds);

}
