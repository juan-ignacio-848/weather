package com.nmkip.weather.configuration;

import com.nmkip.weather.domain.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class ForecastConfiguration {

    @Bean
    public WeatherForecastReporter weatherForecastReporter() {
        Planet feringi = new Planet(500, 1);
        Planet vulcano = new Planet(1000, -5);
        Planet betasoide = new Planet(2000, 3);
        List<Planet> planets = List.of(feringi, vulcano, betasoide);
        return new WeatherForecastReporter(new SolarSystemSimulator(planets), new SolarSystemAnalytics(), new WeatherForecaster());
    }
}
