package com.example.dibchallenge.beer;

import java.math.BigDecimal;

public class BeerDto {

    private Long id;

    private String name;

    private String description;

    private BigDecimal meanTemperature;

    public BeerDto() {
    }

    private BeerDto(Long id, String name, String description, BigDecimal meanTemperature) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.meanTemperature = meanTemperature;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getMeanTemperature() {
        return meanTemperature;
    }

    public void setMeanTemperature(BigDecimal meanTemperature) {
        this.meanTemperature = meanTemperature;
    }

    public static class Builder {

        private Long id;
        private String name;
        private String description;
        private BigDecimal meanTemperature;

        public Builder setId(Long id) {
            this.id = id;
            return this;
        }

        public Builder setName(String name) {
            this.name = name;
            return this;
        }

        public Builder setDescription(String description) {
            this.description = description;
            return this;
        }

        public Builder setMeanTemperature(BigDecimal meanTemperature) {
            this.meanTemperature = meanTemperature;
            return this;
        }

        public BeerDto build() {
            return new BeerDto(id, name, description, meanTemperature);
        }
    }

}
