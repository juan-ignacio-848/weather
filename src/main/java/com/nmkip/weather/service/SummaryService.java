package com.nmkip.weather.service;

import com.nmkip.weather.domain.Summary;
import com.nmkip.weather.exception.NotFoundException;
import com.nmkip.weather.repository.SummaryRepository;
import org.springframework.stereotype.Component;

@Component
public class SummaryService {

    private static final Long SUMMARY_ID = 1L;
    private static final String SUMMARY_NOT_FOUND = "Summary not found";

    private final SummaryRepository repository;

    public SummaryService(SummaryRepository repository) {
        this.repository = repository;
    }

    public Summary summaryForNext(Integer years) {
        return repository.findById(SUMMARY_ID)
                .map(summary -> new Summary(summary.getId(),
                        summary.getOptimalCount() * years,
                        summary.getDraughtCount() * years,
                        summary.getRainyCount() * years,
                        summary.getUnknownCount() * years,
                        summary.getMostIntenseRainyDay()))
                .orElseThrow(() -> new NotFoundException(SUMMARY_NOT_FOUND));
    }
}
