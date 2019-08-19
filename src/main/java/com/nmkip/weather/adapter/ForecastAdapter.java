package com.nmkip.weather.adapter;

import com.nmkip.weather.domain.Forecast;
import com.nmkip.weather.dto.response.ForecastResponse;
import org.springframework.stereotype.Component;

@Component
public class ForecastAdapter {

    public ForecastResponse toDto(Forecast forecast) {
        return new ForecastResponse(forecast.getDay(), forecast.getWeather());
    }
}
