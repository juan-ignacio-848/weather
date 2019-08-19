package com.nmkip.weather.domain;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Objects;

@Entity
public class Summary {

    @Id
    private Long id;
    private Long optimalCount;
    private Long draughtCount;
    private Long rainyCount;
    private Long unknownCount;
    private Integer mostIntenseRainyDay;

    public Summary() {
    }

    public Summary(Long id, Long optimalCount, Long draughtCount, Long rainyCount, Long unknownCount, Integer mostIntenseRainyDay) {
        this.id = id;
        this.optimalCount = optimalCount;
        this.draughtCount = draughtCount;
        this.rainyCount = rainyCount;
        this.unknownCount = unknownCount;
        this.mostIntenseRainyDay = mostIntenseRainyDay;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getOptimalCount() {
        return optimalCount;
    }

    public void setOptimalCount(Long optimalCount) {
        this.optimalCount = optimalCount;
    }

    public Long getDraughtCount() {
        return draughtCount;
    }

    public void setDraughtCount(Long draughtCount) {
        this.draughtCount = draughtCount;
    }

    public Long getRainyCount() {
        return rainyCount;
    }

    public void setRainyCount(Long rainyCount) {
        this.rainyCount = rainyCount;
    }

    public Long getUnknownCount() {
        return unknownCount;
    }

    public void setUnknownCount(Long unknownCount) {
        this.unknownCount = unknownCount;
    }

    public Integer getMostIntenseRainyDay() {
        return mostIntenseRainyDay;
    }

    public void setMostIntenseRainyDay(Integer mostIntenseRainyDay) {
        this.mostIntenseRainyDay = mostIntenseRainyDay;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Summary)) return false;
        Summary summary = (Summary) o;
        return Objects.equals(id, summary.id) &&
                Objects.equals(optimalCount, summary.optimalCount) &&
                Objects.equals(draughtCount, summary.draughtCount) &&
                Objects.equals(rainyCount, summary.rainyCount) &&
                Objects.equals(unknownCount, summary.unknownCount) &&
                Objects.equals(mostIntenseRainyDay, summary.mostIntenseRainyDay);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, optimalCount, draughtCount, rainyCount, unknownCount, mostIntenseRainyDay);
    }
}
