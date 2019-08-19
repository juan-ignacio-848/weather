package com.nmkip.weather.domain;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

class SolarSystemSimulator {

    private final List<Planet> planets;

    SolarSystemSimulator(List<Planet> planets) {
        this.planets = planets;
    }

    List<SolarSystemState> simulate(int days) {
        if (days == 0) {
            return Collections.emptyList();
        }

        return Stream.iterate(1, n -> n + 1)
                .limit(days)
                .map(day -> new SolarSystemState(day, advanceTo(day, planets)))
                .collect(Collectors.toList());
    }

    private List<Coordinate> advanceTo(Integer day, List<Planet> planets) {
        return planets
                .stream()
                .map(planet -> planet.advanceTo(day))
                .collect(Collectors.toList());
    }

}
