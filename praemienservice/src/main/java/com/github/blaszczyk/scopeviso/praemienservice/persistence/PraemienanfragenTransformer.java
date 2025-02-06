package com.github.blaszczyk.scopeviso.praemienservice.persistence;

import com.github.blaszczyk.scopeviso.praemienservice.domain.PraemienAnfrageRequest;

import java.util.UUID;

public class PraemienanfragenTransformer {
    public static Praemienanfragen transform(final PraemienAnfrageRequest request, final int praemie) {
        final var result = new Praemienanfragen();
        result.setId(UUID.randomUUID());
        result.setBundesland(request.bundesland());
        result.setKreis(request.kreis());
        result.setStadt(request.stadt());
        result.setPostleitzahl(request.postleitzahl());
        result.setBezirk(request.bezirk());
        result.setFahrzeugtyp(request.fahrzeugtyp());
        result.setKilometerleistung(request.kilometerleistung());
        result.setPraemie(praemie);
        return result;
    }
}
