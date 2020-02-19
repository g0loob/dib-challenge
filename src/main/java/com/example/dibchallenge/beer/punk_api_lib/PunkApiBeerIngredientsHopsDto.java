package com.example.dibchallenge.beer.punk_api_lib;

public class PunkApiBeerIngredientsHopsDto extends PunkApiBeerNameAndAmmountWrapperDto {

    private String add;
    private String attribute;

    public PunkApiBeerIngredientsHopsDto() {
        super();
    }

    public String getAdd() {
        return add;
    }

    public void setAdd(String add) {
        this.add = add;
    }

    public String getAttribute() {
        return attribute;
    }

    public void setAttribute(String attribute) {
        this.attribute = attribute;
    }

}
