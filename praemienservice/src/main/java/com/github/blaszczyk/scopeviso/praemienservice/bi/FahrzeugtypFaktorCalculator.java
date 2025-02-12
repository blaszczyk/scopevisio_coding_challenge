package com.github.blaszczyk.scopeviso.praemienservice.bi;

import com.github.blaszczyk.scopeviso.praemienservice.domain.Fahrzeugtyp;

public final class FahrzeugtypFaktorCalculator {
    public static float calculate(final Fahrzeugtyp fahrzeugtyp) {
        return switch (fahrzeugtyp) {
            case LKW -> 1000.f;
            case PKW -> 500.f;
            case ZWEIRAD -> 300.f;
        };
    }

    private FahrzeugtypFaktorCalculator() {}
}
