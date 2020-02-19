package com.example.dibchallenge.beer.punk_api_lib;

import java.math.BigDecimal;

public class PunkApiBeerUnitAndValueDto {

    private BigDecimal value;
    private String unit;

    public PunkApiBeerUnitAndValueDto() {
    }

    public BigDecimal getValue() {
        return value;
    }

    public void setValue(BigDecimal value) {
        this.value = value;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }
}
