package com.nmkip.weather.domain;

import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

import static com.nmkip.weather.domain.Weather.RAINY;
import static java.util.Comparator.comparing;

public class WeatherForecastReporter {

    private final SolarSystemSimulator simulator;
    private final SolarSystemAnalytics analytics;
    private final WeatherForecaster weatherForecaster;

    public WeatherForecastReporter(SolarSystemSimulator solarSystemSimulator, SolarSystemAnalytics analytics, WeatherForecaster weatherForecaster) {
        this.simulator = solarSystemSimulator;
        this.analytics = analytics;
        this.weatherForecaster = weatherForecaster;
    }

    public WeatherForecastReport reportForFollowing(int days) {
        final Map<SolarSystemState, Weather> weatherByState = weatherByState(simulator.simulate(days));
        return new WeatherForecastReport(forecastsFor(weatherByState),
                weatherCount(weatherByState.values()),
                mostIntenseRainyDay(weatherByState));
    }

    private List<Forecast> forecastsFor(Map<SolarSystemState, Weather> solarSystemProjection) {
        return solarSystemProjection
                .entrySet()
                .stream()
                .map(entry -> new Forecast(entry.getKey().day(), entry.getValue()))
                .collect(Collectors.toList());
    }

    private Map<Weather, Long> weatherCount(Collection<Weather> weathers) {
        return weathers
                .stream()
                .collect(Collectors.groupingBy(
                        Function.identity(),
                        Collectors.counting()
                ));
    }

    private Integer mostIntenseRainyDay(Map<SolarSystemState, Weather> solarSystemProjection) {
        return solarSystemProjection
                .entrySet()
                .stream()
                .filter(stateWeatherEntry -> stateWeatherEntry.getValue().equals(RAINY))
                .max(comparing(stateWeatherEntry -> analytics.squaredDistanceBetween(stateWeatherEntry.getKey().coordinates())))
                .map(stateWeatherEntry -> stateWeatherEntry.getKey().day())
                .orElse(0);
    }

    private Map<SolarSystemState, Weather> weatherByState(List<SolarSystemState> solarSystemProjection) {
        return solarSystemProjection
                .stream()
                .collect(Collectors.toMap(
                        Function.identity(),
                        state -> weatherForecaster.weatherBy(planetsAlignment(state)),
                        (weather, weather2) -> weather,
                        LinkedHashMap::new));
    }

    private Alignment planetsAlignment(SolarSystemState state) {
        return analytics.alignmentOf(state.coordinates());
    }

}
