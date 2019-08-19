package com.nmkip.weather.dto.response;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

import java.util.Objects;

@JsonNaming(value = PropertyNamingStrategy.SnakeCaseStrategy.class)
public class SummaryResponse {

    private final Long optimalCount;
    private final Long draughtCount;
    private final Long rainyCount;
    private final Long unknownCount;
    private final Integer mostIntenseRainyDay;

    public SummaryResponse(Long optimalCount, Long draughtCount, Long rainyCount, Long unknownCount, Integer mostIntenseRainyDay) {
        this.optimalCount = optimalCount;
        this.draughtCount = draughtCount;
        this.rainyCount = rainyCount;
        this.unknownCount = unknownCount;
        this.mostIntenseRainyDay = mostIntenseRainyDay;
    }

    public Long getOptimalCount() {
        return optimalCount;
    }

    public Long getDraughtCount() {
        return draughtCount;
    }

    public Long getRainyCount() {
        return rainyCount;
    }

    public Long getUnknownCount() {
        return unknownCount;
    }

    public Integer getMostIntenseRainyDay() {
        return mostIntenseRainyDay;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof SummaryResponse)) return false;
        SummaryResponse that = (SummaryResponse) o;
        return Objects.equals(optimalCount, that.optimalCount) &&
                Objects.equals(draughtCount, that.draughtCount) &&
                Objects.equals(rainyCount, that.rainyCount) &&
                Objects.equals(unknownCount, that.unknownCount) &&
                Objects.equals(mostIntenseRainyDay, that.mostIntenseRainyDay);
    }

    @Override
    public int hashCode() {
        return Objects.hash(optimalCount, draughtCount, rainyCount, unknownCount, mostIntenseRainyDay);
    }
}
