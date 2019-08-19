package com.nmkip.weather.domain;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import java.util.Objects;

@Entity
public class Forecast {

    @Id
    private int day;

    @Enumerated(EnumType.STRING)
    private Weather weather;

    public Forecast() {

    }

    Forecast(int day, Weather weather) {
        this.day = day;
        this.weather = weather;
    }

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public Weather getWeather() {
        return weather;
    }

    public void setWeather(Weather weather) {
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
