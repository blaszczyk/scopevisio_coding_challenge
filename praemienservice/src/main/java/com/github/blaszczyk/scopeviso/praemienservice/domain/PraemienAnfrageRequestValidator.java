package com.github.blaszczyk.scopeviso.praemienservice.domain;

import com.github.blaszczyk.scopeviso.praemienservice.exception.UnknownLocationException;

import java.util.List;
import java.util.function.Consumer;

public class PraemienAnfrageRequestValidator {

    public static Consumer<? super List<Location>> validate(PraemienAnfrageRequest anfrage) {
        final Location anfrageLocation = new Location(anfrage.bundesland(), anfrage.kreis(), anfrage.stadt(), anfrage.postleitzahl(), anfrage.bezirk());
        return responseLocations -> {
            if (!responseLocations.contains(anfrageLocation)){
                throw new UnknownLocationException(anfrageLocation);
            }
        };
    }
}
