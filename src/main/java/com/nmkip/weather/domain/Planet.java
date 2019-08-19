package com.nmkip.weather.domain;

import java.util.Objects;

class Planet {

    private final int distance;
    private final int speed;

    Planet(int distance, int speed) {
        this.distance = distance;
        this.speed = speed;
    }

    Coordinate advanceTo(Integer day) {
        return Coordinate.translate(distance, speed * day);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Planet)) return false;
        Planet planet = (Planet) o;
        return distance == planet.distance &&
                speed == planet.speed;
    }

    @Override
    public int hashCode() {
        return Objects.hash(distance, speed);
    }

    @Override
    public String toString() {
        return "Planet{" +
                " distance=" + distance +
                ", speed=" + speed +
                '}';
    }
}
