package com.nmkip.weather.controller;

import com.nmkip.weather.adapter.ForecastAdapter;
import com.nmkip.weather.dto.request.WeatherRequest;
import com.nmkip.weather.dto.response.ForecastResponse;
import com.nmkip.weather.service.WeatherService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
public class WeatherController {

    private final WeatherService weatherService;
    private final ForecastAdapter forecastAdapter;

    public WeatherController(WeatherService weatherService, ForecastAdapter forecastAdapter) {
        this.weatherService = weatherService;
        this.forecastAdapter = forecastAdapter;
    }

    @GetMapping("/weather")
    ForecastResponse forecastFor(@Valid WeatherRequest request) {
        return forecastAdapter.toDto(weatherService.forecastFor(request.getDay()));
    }
}
