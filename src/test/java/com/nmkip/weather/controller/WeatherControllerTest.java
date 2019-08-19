package com.nmkip.weather.controller;

import com.nmkip.weather.adapter.ForecastAdapter;
import com.nmkip.weather.domain.Forecast;
import com.nmkip.weather.dto.request.WeatherRequest;
import com.nmkip.weather.dto.response.ForecastResponse;
import com.nmkip.weather.service.WeatherService;
import name.falgout.jeffrey.testing.junit.mockito.MockitoExtension;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;

import static com.nmkip.weather.domain.Weather.RAINY;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
class WeatherControllerTest {

    @Mock
    private WeatherService weatherService;

    private WeatherController weatherController;

    @BeforeEach
    void setUp() {
        ForecastAdapter forecastAdapter = new ForecastAdapter();
        weatherController = new WeatherController(weatherService, forecastAdapter);
    }

    @Test
    void search_for_forecast_of_a_given_day() {
        given(weatherService.forecastFor(1)).willReturn(new Forecast(1, RAINY));
        assertThat(weatherController.forecastFor(new WeatherRequest(1)), is(new ForecastResponse(1, RAINY)));
    }
}
