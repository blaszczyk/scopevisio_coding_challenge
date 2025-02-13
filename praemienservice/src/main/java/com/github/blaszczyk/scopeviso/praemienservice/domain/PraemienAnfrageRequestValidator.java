package com.github.blaszczyk.scopeviso.praemienservice.domain;

import com.github.blaszczyk.scopeviso.praemienservice.exception.UnknownLocationException;

import java.util.List;
import java.util.function.Consumer;
import java.util.regex.Pattern;

public class PraemienAnfrageRequestValidator {

    private static final Pattern PLZ_PATTERN = Pattern.compile("\\d{5}");

    public static boolean validateInput(PraemienAnfrageRequest request) {
        return request.postleitzahl() != null
                && PLZ_PATTERN.matcher(request.postleitzahl()).matches()
                && request.kilometerleistung() > 0
                && request.fahrzeugtyp() != null;
    }

    public static Consumer<? super List<Location>> validateLocation(PraemienAnfrageRequest anfrage) {
        final Location anfrageLocation = new Location(anfrage.bundesland(), anfrage.kreis(), anfrage.stadt(), anfrage.postleitzahl(), anfrage.bezirk());
        return responseLocations -> {
            if (!responseLocations.contains(anfrageLocation)){
                throw new UnknownLocationException(anfrageLocation);
            }
        };
    }

    private PraemienAnfrageRequestValidator() {}
}
