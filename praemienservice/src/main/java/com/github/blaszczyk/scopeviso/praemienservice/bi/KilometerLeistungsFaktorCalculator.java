package com.github.blaszczyk.scopeviso.praemienservice.bi;

public final class KilometerLeistungsFaktorCalculator {
    public static float calculate(final int kilometerleistung) {
        if (kilometerleistung <= 5000) {
            return 0.5f;
        } else if (kilometerleistung <= 10000) {
            return 1f;
        } else if (kilometerleistung <= 20000) {
            return 1.5f;
        } else {
            return 2f;
        }
    }
}
