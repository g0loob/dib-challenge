package com.example.dibchallenge.beer.mash_temp;

import com.example.dibchallenge.beer.BeerEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.math.BigDecimal;

@Entity
@Table(name = "beer_mash_temperature", schema = "public")
public class BeerMashTemperatureEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "value", scale = 13, precision = 4, nullable = false)
    private BigDecimal value;

    @ManyToOne(fetch = FetchType.LAZY)
    private BeerEntity beer;

    public BeerMashTemperatureEntity() {
    }

    public BeerMashTemperatureEntity(BigDecimal value, BeerEntity beerEntity) {
        this.value = value;
        this.beer = beerEntity;
    }

    public Long getId() {
        return id;
    }

    public BigDecimal getValue() {
        return value;
    }

    public void setValue(BigDecimal value) {
        this.value = value;
    }

    public BeerEntity getBeer() {
        return beer;
    }

    public void setBeer(BeerEntity beer) {
        this.beer = beer;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (!(o instanceof BeerMashTemperatureEntity))
            return false;

        BeerMashTemperatureEntity other = (BeerMashTemperatureEntity) o;

        return id != null &&
                id.equals(other.getId());
    }

    @Override
    public int hashCode() {
        return 31;
    }

}
