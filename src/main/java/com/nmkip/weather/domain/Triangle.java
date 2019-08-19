package com.nmkip.weather.domain;

import static java.lang.Math.max;
import static java.lang.Math.min;

class Triangle {

    private Coordinate c1;
    private Coordinate c2;
    private Coordinate c3;

    private Triangle(Coordinate c1, Coordinate c2, Coordinate c3) {
        this.c1 = c1;
        this.c2 = c2;
        this.c3 = c3;
    }

    static Triangle from(Coordinate c1, Coordinate c2, Coordinate c3) {
        return new Triangle(c1, c2, c3);
    }

    Boolean contains(Coordinate p) {
        return inTheBorders(p) || isInside(p);
    }

    private boolean inTheBorders(Coordinate p) {
        return withinBounds(p) &&
                (LinearFunction.from(c1, c2).contains(p) ||
                 LinearFunction.from(c1, c3).contains(p) ||
                 LinearFunction.from(c2, c3).contains(p));

    }

    private boolean withinBounds(Coordinate p) {
        return min_X() <= p.X() &&
               p.X() <= max_X() &&
               min_Y() <= p.Y() &&
               p.Y() <= max_Y();
    }

    private double max_X() {
        return max(max(c1.X(), c2.X()), c3.X());
    }

    private double max_Y() {
        return max(max(c1.Y(), c2.Y()), c3.Y());
    }

    private double min_X() {
        return min(min(c1.X(), c2.X()), c3.X());
    }

    private double min_Y() {
        return min(min(c1.Y(), c2.Y()), c3.Y());
    }

    /**
     * @see <a href="http://www.dma.fi.upm.es/personal/mabellanas/tfcs/kirkpatrick/Aplicacion/algoritmos.htm#puntoInteriorDefinicion">Punto interior</a>
     */
    private boolean isInside(Coordinate point) {
        return (orientation(c1, c2, c3) >= 0 && orientation(c1, c2, point) >= 0 && orientation(c2, c3, point) >= 0 && orientation(c3, c1, point) >= 0)
                ||
                (orientation(c1, c2, c3) < 0 && orientation(c1, c2, point) < 0 && orientation(c2, c3, point) < 0 && orientation(c3, c1, point) < 0);
    }

    private double orientation(Coordinate a1, Coordinate a2, Coordinate a3) {
        return (a1.X() - a3.X()) * (a2.Y() - a3.Y()) - (a1.Y() - a3.Y()) * (a2.X() - a3.X());
    }
}
