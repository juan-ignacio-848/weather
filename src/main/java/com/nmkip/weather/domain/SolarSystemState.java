package com.nmkip.weather.domain;

import java.util.List;
import java.util.Objects;

class SolarSystemState {

    private final int day;
    private final List<Coordinate> coordinates;

    SolarSystemState(int day, List<Coordinate> coordinates) {
        this.day = day;
        this.coordinates = coordinates;
    }

    int day() {
        return day;
    }

    List<Coordinate> coordinates() {
        return coordinates;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof SolarSystemState)) return false;
        SolarSystemState that = (SolarSystemState) o;
        return day == that.day &&
                Objects.equals(coordinates, that.coordinates);
    }

    @Override
    public int hashCode() {
        return Objects.hash(day, coordinates);
    }

    @Override
    public String toString() {
        return "SolarSystemState{" +
                "day=" + day +
                ", coordinates=" + coordinates +
                '}';
    }
}
