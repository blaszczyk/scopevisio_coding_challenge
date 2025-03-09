package com.github.blaszczyk.scopeviso.praemienservice.persistence;

import com.github.blaszczyk.scopeviso.praemienservice.domain.Location;
import com.github.blaszczyk.scopeviso.praemienservice.domain.PraemienAntragRequest;
import com.github.blaszczyk.scopeviso.praemienservice.domain.PraemienAntragResponse;
import com.github.blaszczyk.scopeviso.praemienservice.domain.PraemienAntragSummary;

public final class PraemienAntragTransformer {
    public static PraemienAntragEntity transformToEntity(final PraemienAntragRequest request, final int praemie) {
        final var location = request.ort();
        final var result = new PraemienAntragEntity();
        result.setBundesland(location.bundesland());
        result.setKreis(location.kreis());
        result.setStadt(location.stadt());
        result.setPostleitzahl(location.postleitzahl());
        result.setBezirk(location.bezirk());
        result.setFahrzeugtyp(request.fahrzeugtyp());
        result.setKilometerleistung(request.kilometerleistung());
        result.setPraemie(praemie);
        return result;
    }

    public static PraemienAntragSummary transformToSummary(final PraemienAntragEntity antrag) {
        final Location ort = new Location(antrag.getBundesland(), antrag.getKreis(), antrag.getStadt(),
                antrag.getPostleitzahl(), antrag.getBezirk());
        return new PraemienAntragSummary(antrag.getId(), antrag.getPraemie(), antrag.getKilometerleistung(),
                antrag.getFahrzeugtyp(), ort);
    }

    public static PraemienAntragResponse transformToResponse(final PraemienAntragEntity antrag) {
        return new PraemienAntragResponse(antrag.getPraemie(), antrag.getId());
    }

    private PraemienAntragTransformer() {}
}
