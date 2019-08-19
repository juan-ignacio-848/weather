package com.nmkip.weather.dto.request;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

public class SummaryRequest {

    @NotNull(message = "Param 'years' is required")
    @Min(value = 1, message = "Param 'years' must be greater or equal than 1")
    private Integer years;

    public SummaryRequest(Integer years) {
        this.years = years;
    }

    public Integer getYears() {
        return years;
    }

}
