package com.nmkip.weather.domain;

import java.util.List;

import static com.nmkip.weather.domain.Alignment.*;
import static java.lang.Math.pow;

public class SolarSystemAnalytics {

    private static final Coordinate SUN_COORDINATE = new Coordinate(0, 0);

    Double squaredDistanceBetween(List<Coordinate> coordinates) {
        Coordinate c1 = coordinates.get(0);
        Coordinate c2 = coordinates.get(1);
        Coordinate c3 = coordinates.get(2);

        return squaredDistanceFrom(c1, c2) + squaredDistanceFrom(c2, c3) + squaredDistanceFrom(c3, c1);
    }

    private Double squaredDistanceFrom(Coordinate c1, Coordinate c2) {
        return pow(c2.X() - c1.X(), 2) + pow(c2.Y() - c1.Y(), 2);
    }

    Alignment alignmentOf(List<Coordinate> coordinates) {
        Coordinate c1 = coordinates.get(0);
        Coordinate c2 = coordinates.get(1);
        Coordinate c3 = coordinates.get(2);

        final LinearFunction line = LinearFunction.from(c1, c2);
        if (line.contains(c3, SUN_COORDINATE)) {
            return LINE_OF_PLANETS_AND_SUN;
        } else if (line.contains(c3)) {
            return LINE_OF_PLANETS;
        }

        final Triangle triangle = Triangle.from(c1, c2, c3);
        if (triangle.contains(SUN_COORDINATE)) {
            return TRIANGLE_CONTAINING_SUN;
        } else {
            return TRIANGLE;
        }
    }

}
