package com.nmkip.weather.dto.request;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

public class WeatherRequest {

    @NotNull(message = "Param 'day' is required")
    @Min(value = 1, message = "Param 'day' must be greater or equal than 1")
    private Integer day;

    public WeatherRequest(Integer day) {
        this.day = day;
    }

    public Integer getDay() {
        return day;
    }

}
