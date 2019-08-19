package com.nmkip.weather.domain;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Objects;

class Coordinate {
    private final double x;
    private final double y;

    Coordinate(double x, double y) {
        this.x = x;
        this.y = y;
    }

    static Coordinate translate(double distance, int angle) {
        return new Coordinate(x(distance, angle), y(distance, angle));
    }

    private static double x(double distance, int speed) {
        return toFixedTwoDecimals(Math.cos(Math.toRadians(speed)) * distance);
    }

    private static double y(double distance, int speed) {
        return toFixedTwoDecimals(Math.sin(Math.toRadians(speed)) * distance);
    }

    private static double toFixedTwoDecimals(double n) {
        return BigDecimal.valueOf(n).setScale(2, RoundingMode.HALF_DOWN).doubleValue();
    }

    double X() {
        return x;
    }

    double Y() {
        return y;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Coordinate)) return false;
        Coordinate that = (Coordinate) o;
        return Double.compare(that.x, x) == 0 &&
                Double.compare(that.y, y) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }

    @Override
    public String toString() {
        return "(" + x + " " + y + ")";
    }
}
