package com.nmkip.weather.domain;

import java.util.Objects;

class Forecast {

    private final int day;
    private final Weather weather;

    Forecast(int day, Weather weather) {
        this.day = day;
        this.weather = weather;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Forecast)) return false;
        Forecast forecast = (Forecast) o;
        return day == forecast.day &&
                Objects.equals(weather, forecast.weather);
    }

    @Override
    public int hashCode() {
        return Objects.hash(day, weather);
    }

    @Override
    public String toString() {
        return "{" + day + " " + weather + "}";
    }
}
