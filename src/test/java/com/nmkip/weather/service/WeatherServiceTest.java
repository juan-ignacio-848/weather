package com.nmkip.weather.service;

import com.nmkip.weather.domain.Forecast;
import com.nmkip.weather.exception.ForecastNotFoundException;
import com.nmkip.weather.repository.ForecastRepository;
import name.falgout.jeffrey.testing.junit.mockito.MockitoExtension;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.mockito.Mock;

import java.util.Optional;

import static com.nmkip.weather.domain.Weather.DRAUGHT;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
class WeatherServiceTest {

    @Mock
    ForecastRepository forecastRepository;
    private WeatherService service;

    @BeforeEach
    void setUp() {
        service = new WeatherService(forecastRepository);
    }

    @ParameterizedTest
    @CsvSource({
            "100, 100",
            "460, 100",
            "360, 360",
            "720, 360"

    })
    void search_for_forecast_by_day(Integer day, Integer equivalentDay) {
        given(forecastRepository.findById(equivalentDay)).willReturn(Optional.of(new Forecast(equivalentDay, DRAUGHT)));

        assertThat(service.forecastFor(day), is(new Forecast(day, DRAUGHT)));
    }

    @Test
    void throw_an_exception_when_day_is_not_found() {
        given(forecastRepository.findById(32)).willReturn(Optional.empty());

        Assertions.assertThrows(ForecastNotFoundException.class, () -> {
            service.forecastFor(32);
        });
    }
}
