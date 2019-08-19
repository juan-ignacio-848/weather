package com.nmkip.weather.adapter;

import com.nmkip.weather.domain.Summary;
import com.nmkip.weather.dto.response.SummaryResponse;
import org.springframework.stereotype.Component;

@Component
public class SummaryAdapter {

    public SummaryResponse toDto(Summary summary) {
        return new SummaryResponse(summary.getOptimalCount(),
                                   summary.getDraughtCount(),
                                   summary.getRainyCount(),
                                   summary.getUnknownCount(),
                                   summary.getMostIntenseRainyDay());
    }
}
