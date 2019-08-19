package com.nmkip.weather.controller;

import com.nmkip.weather.adapter.ForecastAdapter;
import com.nmkip.weather.domain.Forecast;
import com.nmkip.weather.exception.ForecastNotFoundException;
import com.nmkip.weather.exception.WeatherControllerAdvice;
import com.nmkip.weather.service.WeatherService;
import name.falgout.jeffrey.testing.junit.mockito.MockitoExtension;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static com.nmkip.weather.domain.Weather.RAINY;
import static org.hamcrest.CoreMatchers.is;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
class WeatherControllerAPITest {

    @Mock
    private WeatherService weatherService;

    private MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        WeatherController weatherController = new WeatherController(weatherService, new ForecastAdapter());
        mockMvc = MockMvcBuilders.standaloneSetup(weatherController)
                .setControllerAdvice(new WeatherControllerAdvice())
                .build();
    }

    @Test
    void validate_status_ok_json_format() throws Exception {
        given(weatherService.forecastFor(1)).willReturn(new Forecast(1, RAINY));
        mockMvc.perform(get("/weather?day=1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.day", is(1)))
                .andExpect(jsonPath("$.weather", is("RAINY")));
    }

    @Test
    void validate_status_bad_request_when_day_is_less_than_1() throws Exception {
        given(weatherService.forecastFor(1)).willReturn(new Forecast(1, RAINY));
        mockMvc.perform(get("/weather?day=0"))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.message", is("First day is 1")));
    }

    @Test
    void validate_status_bad_request_when_day_is_missing() throws Exception {
        given(weatherService.forecastFor(1)).willReturn(new Forecast(1, RAINY));
        mockMvc.perform(get("/weather"))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.message", is("A day number is required")));
    }

    @Test
    void validate_status_is_not_found_when_a_forecast_is_missing() throws Exception {
        given(weatherService.forecastFor(23341)).willThrow(ForecastNotFoundException.class);
        mockMvc.perform(get("/weather?day=23341"))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.message", is("Forecast not found")));
    }

    @Test
    void validate_status_is_internal_server_error_when_unexpected_exception_occurs() throws Exception {
        given(weatherService.forecastFor(4)).willThrow(RuntimeException.class);
        mockMvc.perform(get("/weather?day=4"))
                .andExpect(status().isInternalServerError())
                .andExpect(jsonPath("$.message", is("Something went wrong")));

    }
}
