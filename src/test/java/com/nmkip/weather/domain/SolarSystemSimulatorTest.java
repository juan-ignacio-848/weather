package com.nmkip.weather.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static java.util.Collections.emptyList;
import static java.util.Collections.singletonList;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

@DisplayName("Solar System Simulator should")
class SolarSystemSimulatorTest {

    private SolarSystemSimulator simulator;

    @BeforeEach
    void setUp() {
        Planet feringi = new Planet(500, 1);
        Planet vulcano = new Planet(1000, -5);
        Planet betasoide = new Planet(2000, 3);
        List<Planet> planets = List.of(feringi, vulcano, betasoide);
        simulator = new SolarSystemSimulator(planets);
    }

    @Test
    @DisplayName("Simulate zero days")
    void simulate_zero_days() {
        assertThat(simulator.simulate(0), is(emptyList()));
    }

    @Test
    @DisplayName("Simulate one day")
    void simulate_one_day() {
        assertThat(simulator.simulate(1), is(singletonList(
                new SolarSystemState(1, List.of(
                        new Coordinate(499.92, 8.73),
                        new Coordinate(996.19, -87.16),
                        new Coordinate(1997.26, 104.67)))
        )));
    }

    @Test
    @DisplayName("Simulate two days")
    void simulate_two_days() {
        assertThat(simulator.simulate(2), is(List.of(
                new SolarSystemState(1, List.of(
                        new Coordinate(499.92, 8.73),
                        new Coordinate(996.19, -87.16),
                        new Coordinate(1997.26, 104.67)
                )),
                new SolarSystemState(2, List.of(
                        new Coordinate(499.7, 17.45),
                        new Coordinate(984.81, -173.65),
                        new Coordinate(1989.04, 209.06)
                ))
        )));
    }
}
