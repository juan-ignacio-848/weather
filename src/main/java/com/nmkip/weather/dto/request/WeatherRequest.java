package com.nmkip.weather.dto.request;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

public class WeatherRequest {

    @NotNull(message = "A day number is required")
    @Min(value = 1, message = "First day is 1")
    private Integer day;

    public WeatherRequest(@Min(22) Integer day) {
        this.day = day;
    }

    public Integer getDay() {
        return day;
    }

    public void setDay(Integer day) {
        this.day = day;
    }
}
