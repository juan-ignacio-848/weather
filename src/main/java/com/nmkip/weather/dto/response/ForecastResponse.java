package com.nmkip.weather.dto.response;

import com.nmkip.weather.domain.Weather;

import java.util.Objects;

public class ForecastResponse {

    private final Integer day;
    private final Weather weather;

    public ForecastResponse(Integer day, Weather weather) {
        this.day = day;
        this.weather = weather;
    }

    public Integer getDay() {
        return day;
    }

    public Weather getWeather() {
        return weather;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ForecastResponse)) return false;
        ForecastResponse that = (ForecastResponse) o;
        return day.equals(that.day) &&
                weather == that.weather;
    }

    @Override
    public int hashCode() {
        return Objects.hash(day, weather);
    }
}
