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

    private final ForecastService service;
    private final ForecastAdapter adapter;

    public ForecastController(ForecastService service, ForecastAdapter adapter) {
        this.service = service;
        this.adapter = adapter;
    }

    @GetMapping("/weather")
    public ForecastResponse forecastFor(@Valid WeatherRequest request) {
        return adapter.toDto(service.forecastFor(request.getDay()));
    }
}
