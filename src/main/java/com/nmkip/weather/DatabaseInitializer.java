package com.nmkip.weather;

import com.nmkip.weather.domain.Summary;
import com.nmkip.weather.domain.Weather;
import com.nmkip.weather.domain.WeatherForecastReport;
import com.nmkip.weather.domain.WeatherForecastReporter;
import com.nmkip.weather.repository.ForecastRepository;
import com.nmkip.weather.repository.SummaryRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;

@Component
@ConditionalOnProperty(name = "app.db-init", havingValue = "true")
public class DatabaseInitializer implements CommandLineRunner {

    ForecastRepository forecastRepository;
    SummaryRepository summaryRepository;
    WeatherForecastReporter reporter;

    public DatabaseInitializer(SummaryRepository summaryRepository, ForecastRepository forecastRepository, WeatherForecastReporter reporter) {
        this.summaryRepository = summaryRepository;
        this.forecastRepository = forecastRepository;
        this.reporter = reporter;
    }

    @Override
    public void run(String... args) throws Exception {
        System.out.println("Iniciando carga en base");
        final WeatherForecastReport report = reporter.reportForFollowing(360);
        forecastRepository.saveAll(report.forecasts());
        summaryRepository.save(summaryFrom(report));
        System.out.println("Base inicializada");
    }

    private Summary summaryFrom(WeatherForecastReport weatherForecastReport) {
        return new Summary(1L,
                weatherForecastReport.weatherCount().get(Weather.OPTIMAL),
                weatherForecastReport.weatherCount().get(Weather.DRAUGHT),
                weatherForecastReport.weatherCount().get(Weather.RAINY),
                weatherForecastReport.weatherCount().get(Weather.UNKNOWN),
                weatherForecastReport.mostIntenseRainyDay());
    }
}

