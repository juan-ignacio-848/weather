package com.nmkip.weather.controller;

import com.nmkip.weather.adapter.SummaryAdapter;
import com.nmkip.weather.domain.Summary;
import com.nmkip.weather.exception.NotFoundException;
import com.nmkip.weather.exception.WeatherControllerAdvice;
import com.nmkip.weather.service.SummaryService;
import name.falgout.jeffrey.testing.junit.mockito.MockitoExtension;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.hamcrest.CoreMatchers.is;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
class SummaryControllerTest {

    private static final String SUMMARY_NOT_FOUND = "Summary not found";

    @Mock
    private SummaryService service;

    private MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        SummaryController forecastController = new SummaryController(service, new SummaryAdapter());
        mockMvc = MockMvcBuilders.standaloneSetup(forecastController)
                .setControllerAdvice(new WeatherControllerAdvice())
                .build();
    }

    @Test
    void validate_status_ok_json_format_when_searching_for_a_specific_forecast() throws Exception {
        given(service.summaryForNext(10)).willReturn(new Summary(1L, 20L, 5L, 3L, 3000L, 6));
        mockMvc.perform(get("/weather/summary?years=10"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.optimal_count", is(20)))
                .andExpect(jsonPath("$.rainy_count", is(3)))
                .andExpect(jsonPath("$.draught_count", is(5)))
                .andExpect(jsonPath("$.unknown_count", is(3000)))
                .andExpect(jsonPath("$.most_intense_rainy_day", is(6)));
    }

    @Test
    void when_searching_for_a_summary_and_years_param_is_missing_then_status_is_bad_request() throws Exception {
        mockMvc.perform(get("/weather/summary"))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.message", is("Param 'years' is required")));
    }

    @Test
    void when_searching_for_a_summary_and_years_param_is_less_than_1_then_status_is_bad_request() throws Exception {
        mockMvc.perform(get("/weather/summary?years=0"))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.message", is("Param 'years' must be greater or equal than 1")));
    }

    @Test
    void when_searching_for_a_summary_and_it_does_not_exist_then_status_is_not_found() throws Exception {
        given(service.summaryForNext(10)).willThrow(new NotFoundException(SUMMARY_NOT_FOUND));
        mockMvc.perform(get("/weather/summary?years=10"))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.message", is(SUMMARY_NOT_FOUND)));
    }
}
