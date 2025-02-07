package com.github.blaszczyk.scopeviso.praemienservice.persistence;

import com.github.blaszczyk.scopeviso.praemienservice.domain.PraemienAnfrageRequest;
import com.github.blaszczyk.scopeviso.praemienservice.domain.PraemienAnfrageSummary;

import java.util.UUID;

public class PraemienAnfrageTransformer {
    public static PraemienAnfrage transform(final PraemienAnfrageRequest request, final int praemie) {
        final var result = new PraemienAnfrage();
        result.setPraemienId(UUID.randomUUID());
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

    public static PraemienAnfrageSummary transformToSummary(final PraemienAnfrage anfrage) {
        return new PraemienAnfrageSummary(anfrage.getPraemienId(), anfrage.getPraemie(), anfrage.getKilometerleistung(),
                anfrage.getFahrzeugtyp(), anfrage.getBundesland(), anfrage.getKreis(), anfrage.getStadt(),
                anfrage.getPostleitzahl(), anfrage.getBezirk());
    }
}
