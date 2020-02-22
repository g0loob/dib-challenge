package com.example.dibchallenge.punk_api_lib;

public class PunkApiBeerMethodMashTempDto extends PunkApiBeerTempWrapperDto {

    private Long duration;

    public PunkApiBeerMethodMashTempDto() {
        super();
    }

    public Long getDuration() {
        return duration;
    }

    public void setDuration(Long duration) {
        this.duration = duration;
    }
}
