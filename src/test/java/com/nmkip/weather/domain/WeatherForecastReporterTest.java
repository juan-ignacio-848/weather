package com.nmkip.weather.domain;

import name.falgout.jeffrey.testing.junit.mockito.MockitoExtension;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;

import java.util.List;
import java.util.Map;

import static com.nmkip.weather.domain.Alignment.*;
import static com.nmkip.weather.domain.Weather.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
@DisplayName("Weather Forecast Reporter should")
class WeatherForecastReporterTest {

    @Mock
    SolarSystemSimulator simulator;

    @Mock
    SolarSystemAnalytics analytics;

    private WeatherForecastReporter weatherForecastReporter;
    private List<Coordinate> coordinates;

    @BeforeEach
    void setUp() {
        coordinates = List.of(new Coordinate(1.0, 2.0), new Coordinate(3.0, 2.0), new Coordinate(2.0, 5.0));
        WeatherForecaster weatherForecaster = new WeatherForecaster();
        weatherForecastReporter = new WeatherForecastReporter(simulator, analytics, weatherForecaster);
    }

    @Test
    @DisplayName("generate a report based on a simulation")
    void generate_report_based_on_a_simulation() {
        final int days = 5;
        given(analytics.alignmentOf(coordinates))
                .willReturn(
                        LINE_OF_PLANETS,
                        LINE_OF_PLANETS,
                        LINE_OF_PLANETS_AND_SUN,
                        TRIANGLE_CONTAINING_SUN,
                        TRIANGLE);

        given(simulator.simulate(days))
                .willReturn(List.of(
                        new SolarSystemState(1, coordinates),
                        new SolarSystemState(2, coordinates),
                        new SolarSystemState(3, coordinates),
                        new SolarSystemState(4, coordinates),
                        new SolarSystemState(5, coordinates)
                ));

        WeatherForecastReport report = weatherForecastReporter.reportForFollowing(days);

        assertThat(report, is(new WeatherForecastReport(
                List.of(
                        new Forecast(1, OPTIMAL),
                        new Forecast(2, OPTIMAL),
                        new Forecast(3, DRAUGHT),
                        new Forecast(4, RAINY),
                        new Forecast(5, UNKNOWN)
                ),
                Map.of(
                        OPTIMAL, 2L,
                        RAINY, 1L,
                        DRAUGHT, 1L,
                        UNKNOWN, 1L),
                4)));
    }

    @Test
    @DisplayName("check for the most intense rainy day")
    void check_for_most_intense_rainy_day() {
        final int days = 3;
        given(simulator.simulate(days))
                .willReturn(List.of(
                        new SolarSystemState(1, coordinates),
                        new SolarSystemState(2, coordinates),
                        new SolarSystemState(3, coordinates)
                ));
        given(analytics.squaredDistanceBetween(coordinates)).willReturn(37563.2, 89000.0, 18000.0);
        given(analytics.alignmentOf(coordinates)).willReturn(TRIANGLE_CONTAINING_SUN);

        WeatherForecastReport report = weatherForecastReporter.reportForFollowing(days);

        assertThat(report, is(new WeatherForecastReport(
                List.of(
                        new Forecast(1, RAINY),
                        new Forecast(2, RAINY),
                        new Forecast(3, RAINY)
                ),
                Map.of(
                        OPTIMAL, 0L,
                        RAINY, 3L,
                        DRAUGHT, 0L,
                        UNKNOWN, 0L),
                2)));
    }

}
