package com.nmkip.weather.domain;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

class WeatherForecastReport {

    private final List<Forecast> forecasts;
    private final Map<Weather, Long> weatherCount = initWeatherCount();

    private final Integer mostIntenseRainyDay;

    WeatherForecastReport(List<Forecast> forecasts, Map<Weather, Long> weatherCount, Integer mostIntenseRainyDay) {
        this.forecasts = forecasts;
        this.weatherCount.putAll(weatherCount);
        this.mostIntenseRainyDay = mostIntenseRainyDay;
    }

    private Map<Weather, Long> initWeatherCount() {
        return Stream.of(Weather.values())
                .collect(Collectors.toMap(Function.identity(), w -> 0L));

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof WeatherForecastReport)) return false;
        WeatherForecastReport that = (WeatherForecastReport) o;
        return mostIntenseRainyDay.equals(that.mostIntenseRainyDay) &&
                Objects.equals(forecasts, that.forecasts) &&
                Objects.equals(weatherCount, that.weatherCount);
    }

    @Override
    public int hashCode() {
        return Objects.hash(forecasts, weatherCount, mostIntenseRainyDay);
    }

    @Override
    public String toString() {
        return "WeatherForecastReport{" +
                "forecasts=" + forecasts +
                ", weatherCount=" + weatherCount +
                ", mostIntenseRainyDay=" + mostIntenseRainyDay +
                '}';
    }
}
