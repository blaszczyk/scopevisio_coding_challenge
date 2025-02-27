package com.github.blaszczyk.scopeviso.praemienservice.domain;

import java.util.UUID;

public record PraemienAntragSummary(UUID id, int praemie, int kilometerleistung, Fahrzeugtyp fahrzeugtyp, Location ort) {
}
