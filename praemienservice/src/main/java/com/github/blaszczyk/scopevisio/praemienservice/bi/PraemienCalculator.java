package com.github.blaszczyk.scopevisio.praemienservice.bi;

import com.github.blaszczyk.scopevisio.praemienservice.domain.PraemienAntragRequest;

// for simplicity the business intelligence is hard-coded with made-up values
// in a real application one would probably prefer other solutions such as a separate service
public final class PraemienCalculator {
    public static int calculate(final PraemienAntragRequest antrag) {
        final float floatValue = KilometerLeistungsFaktorCalculator.calculate(antrag.kilometerleistung())
                * FahrzeugtypFaktorCalculator.calculate(antrag.fahrzeugtyp())
                * RegionFaktorCalculator.calculate(antrag.ort().bundesland());
        return Math.round(floatValue);
    }

    private PraemienCalculator() {}
}
