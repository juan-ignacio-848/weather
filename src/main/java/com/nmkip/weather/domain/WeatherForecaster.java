package com.nmkip.weather.domain;

import static com.nmkip.weather.domain.Weather.*;

class WeatherForecaster {

    Weather weatherBy(Alignment alignment) {
        switch (alignment) {
            case LINE_OF_PLANETS:
                return OPTIMAL;
            case TRIANGLE_CONTAINING_SUN:
                return RAINY;
            case LINE_OF_PLANETS_AND_SUN:
                return DRAUGHT;
            default:
                return UNKNOWN;
        }
    }

}
