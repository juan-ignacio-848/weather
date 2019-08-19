package com.nmkip.weather.controller;

import com.nmkip.weather.adapter.SummaryAdapter;
import com.nmkip.weather.dto.request.SummaryRequest;
import com.nmkip.weather.dto.response.SummaryResponse;
import com.nmkip.weather.service.SummaryService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
public class SummaryController {

    private final SummaryService service;
    private final SummaryAdapter adapter;

    public SummaryController(SummaryService summaryService, SummaryAdapter summaryAdapter) {
        this.service = summaryService;
        this.adapter = summaryAdapter;
    }

    @GetMapping("/weather/summary")
    public SummaryResponse summaryFor(@Valid SummaryRequest request) {
        return adapter.toDto(service.summaryForNext(request.getYears()));
    }
}
