package com.nmkip.weather.controller;

import com.nmkip.weather.adapter.ForecastAdapter;
import com.nmkip.weather.dto.request.WeatherRequest;
import com.nmkip.weather.dto.response.ForecastResponse;
import com.nmkip.weather.service.ForecastService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
public class ForecastController {

    private final ForecastService forecastService;
    private final ForecastAdapter forecastAdapter;

    public ForecastController(ForecastService forecastService, ForecastAdapter forecastAdapter) {
        this.forecastService = forecastService;
        this.forecastAdapter = forecastAdapter;
    }

    @GetMapping("/weather")
    public ForecastResponse forecastFor(@Valid WeatherRequest request) {
        return forecastAdapter.toDto(forecastService.forecastFor(request.getDay()));
    }
}
