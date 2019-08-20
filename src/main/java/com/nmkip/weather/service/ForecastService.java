package com.nmkip.weather.service;

import com.nmkip.weather.domain.Forecast;
import com.nmkip.weather.exception.NotFoundException;
import com.nmkip.weather.repository.ForecastRepository;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service
public class ForecastService {

    private static final int TOTAL_DAYS_FOR_FULL_CYCLE = 360;
    private static final String FORECAST_NOT_FOUND = "Forecast not found";

    private final ForecastRepository repository;

    public ForecastService(ForecastRepository repository) {
        this.repository = repository;
    }

    @Cacheable("forecast")
    public Forecast forecastFor(int day) {
        return repository.findById(equivalent(day))
                .map(forecast -> new Forecast(day, forecast.getWeather()))
                .orElseThrow(() -> new NotFoundException(FORECAST_NOT_FOUND));
    }

    private int equivalent(int day) {
        if(day % TOTAL_DAYS_FOR_FULL_CYCLE == 0) {
            return TOTAL_DAYS_FOR_FULL_CYCLE;
        }

        return day % TOTAL_DAYS_FOR_FULL_CYCLE;
    }
}
