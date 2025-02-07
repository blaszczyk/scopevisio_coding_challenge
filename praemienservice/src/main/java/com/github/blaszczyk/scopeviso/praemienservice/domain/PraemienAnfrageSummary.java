package com.github.blaszczyk.scopeviso.praemienservice.domain;

import java.util.UUID;

public record PraemienAnfrageSummary(UUID id, int praemie, int kilometerleistung, Fahrzeugtyp fahrzeugtyp, String bundesland, String kreis, String stadt, String postleitzahl, String bezirk) {
}
