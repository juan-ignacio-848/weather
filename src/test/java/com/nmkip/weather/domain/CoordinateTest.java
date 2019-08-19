package com.nmkip.weather.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

@DisplayName("Coordinate should")
class CoordinateTest {

    @Test
    @DisplayName("Coordinates should be equal when adding 360°")
    void coordinates_should_be_equal_when_adding_360_degrees() {
        assertThat(Coordinate.translate(300.0, 60),
                is(Coordinate.translate(300.0, 420)));
    }

}
