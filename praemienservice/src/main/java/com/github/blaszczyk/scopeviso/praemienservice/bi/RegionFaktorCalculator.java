package com.github.blaszczyk.scopeviso.praemienservice.bi;

import com.github.blaszczyk.scopeviso.praemienservice.exception.UnknownBundeslandException;

public final class RegionFaktorCalculator {
    // TODO: make bundesland an enum ?
    public static float calculate(final String bundesland) {
        return switch (bundesland) {
            case "Baden-Württemberg" -> 1.8f;
            case "Bayern" -> 2.0f;
            case "Berlin" -> 1.4f;
            case "Brandenburg" -> 0.6f;
            case "Bremen" -> 0.9f;
            case "Hamburg" -> 1.42f;
            case "Hessen" -> 1.13f;
            case "Mecklenburg-Vorpommern" -> 0.96f;
            case "Niedersachsen" -> 1.1f;
            case "Nordrhein-Westfalen" -> 1.0f;
            case "Rheinland-Pfalz" -> 0.85f;
            case "Saarland" -> 0.8f;
            case "Sachsen" -> 0.4f;
            case "Sachsen-Anhalt" -> 0.5f;
            case "Schleswig-Holstein" -> 1.3f;
            case "Thüringen" -> 0.7f;
            case "Mallorca" -> 5.0f; // ;)
            default -> throw new UnknownBundeslandException(bundesland);
        };
    }
}
