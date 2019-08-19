package com.nmkip.weather.controller;

import com.nmkip.weather.adapter.ForecastAdapter;
import com.nmkip.weather.domain.Forecast;
import com.nmkip.weather.exception.NotFoundException;
import com.nmkip.weather.exception.WeatherControllerAdvice;
import com.nmkip.weather.service.ForecastService;
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
class ForecastControllerTest {

    private static final String FORECAST_NOT_FOUND = "Forecast not found";

    @Mock
    private ForecastService forecastService;

    private MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        ForecastController forecastController = new ForecastController(forecastService, new ForecastAdapter());
        mockMvc = MockMvcBuilders.standaloneSetup(forecastController)
                .setControllerAdvice(new WeatherControllerAdvice())
                .build();
    }

    @Test
    void validate_status_ok_json_format_when_searching_for_a_specific_forecast() throws Exception {
        given(forecastService.forecastFor(1)).willReturn(new Forecast(1, RAINY));
        mockMvc.perform(get("/weather?day=1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.day", is(1)))
                .andExpect(jsonPath("$.weather", is("RAINY")));
    }

    @Test
    void when_searching_for_a_specific_forecast_and_day_is_less_than_1_then_status_is_bad_request() throws Exception {
        mockMvc.perform(get("/weather?day=0"))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.message", is("Param 'day' must be greater or equal than 1")));
    }

    @Test
    void when_searching_for_a_specific_forecast_and_day_is_missing_then_status_is_bad_request() throws Exception {
        mockMvc.perform(get("/weather"))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.message", is("Param 'day' is required")));
    }

    @Test
    void when_searching_for_a_specific_forecast_and_it_does_not_exist_then_status_is_not_found() throws Exception {
        given(forecastService.forecastFor(23341)).willThrow(new NotFoundException(FORECAST_NOT_FOUND));
        mockMvc.perform(get("/weather?day=23341"))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.message", is(FORECAST_NOT_FOUND)));
    }

    @Test
    void when_searching_for_a_specific_forecast_and_an_unexpected_exception_occurs_then_status_is_internal_server_error() throws Exception {
        given(forecastService.forecastFor(4)).willThrow(RuntimeException.class);
        mockMvc.perform(get("/weather?day=4"))
                .andExpect(status().isInternalServerError())
                .andExpect(jsonPath("$.message", is("Something went wrong")));
    }
}
