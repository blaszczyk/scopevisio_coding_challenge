package com.github.blaszczyk.scopeviso.praemienservice.persistence;

import com.github.blaszczyk.scopeviso.praemienservice.domain.PraemienAntragRequest;
import com.github.blaszczyk.scopeviso.praemienservice.domain.PraemienAntragSummary;

import java.util.UUID;

public final class PraemienAntragTransformer {
    public static PraemienAntragEntity transform(final PraemienAntragRequest request, final int praemie) {
        final var result = new PraemienAntragEntity();
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

    public static PraemienAntragSummary transformToSummary(final PraemienAntragEntity antrag) {
        return new PraemienAntragSummary(antrag.getPraemienId(), antrag.getPraemie(), antrag.getKilometerleistung(),
                antrag.getFahrzeugtyp(), antrag.getBundesland(), antrag.getKreis(), antrag.getStadt(),
                antrag.getPostleitzahl(), antrag.getBezirk());
    }

    private PraemienAntragTransformer() {}
}
