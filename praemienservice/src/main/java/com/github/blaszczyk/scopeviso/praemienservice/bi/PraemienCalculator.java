package com.github.blaszczyk.scopeviso.praemienservice.bi;

import com.github.blaszczyk.scopeviso.praemienservice.domain.PraemienAnfrageRequest;

public final class PraemienCalculator {
    public static int calculate(final PraemienAnfrageRequest anfrage) {
        final float floatValue = KilometerLeistungsFaktorCalculator.calculate(anfrage.kilometerleistung())
                * FahrzeugtypFaktorCalculator.calculate(anfrage.fahrzeugtyp())
                * RegionFaktorCalculator.calculate(anfrage.bundesland());
        return Math.round(floatValue);
    }

    private PraemienCalculator() {}
}
