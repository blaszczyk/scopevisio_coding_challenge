package com.github.blaszczyk.scopeviso.praemienservice.bi;

import com.github.blaszczyk.scopeviso.praemienservice.domain.Fahrzeugtyp;

public class FahrzeugtypFaktorCalculator {
    public static float calculate(final Fahrzeugtyp fahrzeugtyp) {
        return switch (fahrzeugtyp) {
            case LKW -> 2.0f;
            case PKW -> 1.0f;
            case Mopped -> 0.5f;
        };
    }
}
