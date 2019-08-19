package com.nmkip.weather.domain;

import java.util.Arrays;

class LinearFunction {

    private final double slope;
    private final double yIntercept;

    private LinearFunction(double slope, double yIntercept) {
        this.slope = slope;
        this.yIntercept = yIntercept;
    }

    static LinearFunction from(Coordinate c1, Coordinate c2) {
        double slope = (c1.Y() - c2.Y()) / (c1.X() - c2.X());
        double yIntercept = c1.Y() - c1.X() * slope;
        return new LinearFunction(slope, yIntercept);
    }

    Boolean contains(Coordinate... coordinates) {
        return Arrays.stream(coordinates)
                .allMatch(coordinate -> coordinate.Y() == slope * coordinate.X() + yIntercept);
    }
}
