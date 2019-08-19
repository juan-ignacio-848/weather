package com.nmkip.weather.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

@DisplayName("Solar System Analytics should")
class SolarSystemAnalyticsTest {

    private SolarSystemAnalytics analytics;

    @BeforeEach
    void setUp() {
        analytics = new SolarSystemAnalytics();
    }

    @DisplayName("Calculate alignment of coordinates")
    @ParameterizedTest(name = "Coordinates: ({0} {1}) ({2} {3}) ({4} {5}) is {6}")
    @CsvSource({
            "1,1, 2,2, 3,3, LINE_OF_PLANETS_AND_SUN",
            "0,1, 1,2, 2,3, LINE_OF_PLANETS",
            "-1,-2, 3,2, -1,2, TRIANGLE_CONTAINING_SUN",
            "-2,3, 10,-4, 0,0, TRIANGLE_CONTAINING_SUN",
            "10,10, -10,-10, -20,20, TRIANGLE_CONTAINING_SUN",
            "-2,3, 10,-4, 15,5, TRIANGLE",
            "-15,0, -2,3, -20,10, TRIANGLE",
    })
    void calculate_alignment(double x1, double y1, double x2, double y2, double x3, double y3, Alignment alignment) {
        assertThat(analytics.alignmentOf(coordinates(x1, y1, x2, y2, x3, y3)), is(alignment));
    }

    @DisplayName("Calculate distance between coordinates")
    @ParameterizedTest(name = "Coordinates: ({0} {1}) ({2} {3}) ({4} {5}) is {6}")
    @CsvSource({
            "-15,0, -2,3, -20,10, 676.0"
    })
    void calculate_distance_from_point_1_to_point_1(double x1, double y1, double x2, double y2, double x3, double y3, double distance) {
        assertThat(analytics.squaredDistanceBetween(coordinates(x1, y1, x2, y2, x3, y3)), is(distance));
    }

    private List<Coordinate> coordinates(double x1, double y1, double x2, double y2, double x3, double y3) {
        return List.of(new Coordinate(x1, y1), new Coordinate(x2, y2), new Coordinate(x3, y3));
    }
}
