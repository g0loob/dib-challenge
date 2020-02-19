package com.example.dibchallenge.beer.punk_api_lib;

public class PunkApiBeerNameAndAmmountWrapperDto {

    private String name;
    private PunkApiBeerUnitAndValueDto amount;

    public PunkApiBeerNameAndAmmountWrapperDto() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public PunkApiBeerUnitAndValueDto getAmount() {
        return amount;
    }

    public void setAmount(PunkApiBeerUnitAndValueDto amount) {
        this.amount = amount;
    }

}
