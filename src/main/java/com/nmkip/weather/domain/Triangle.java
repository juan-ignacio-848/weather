package com.nmkip.weather.domain;

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

    Boolean contains(Coordinate P) {
        return inTheBorders(P) || isInside(P);
    }

    private boolean inTheBorders(Coordinate P) {
        return LinearFunction.from(c1, c2).contains(P) ||
                LinearFunction.from(c1, c3).contains(P) ||
                LinearFunction.from(c2, c3).contains(P);

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
