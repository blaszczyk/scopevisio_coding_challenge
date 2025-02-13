package com.github.blaszczyk.scopeviso.praemienservice.domain;

import com.github.blaszczyk.scopeviso.praemienservice.exception.UnknownLocationException;

import java.util.List;
import java.util.function.Consumer;
import java.util.regex.Pattern;

public class PraemienAntragRequestValidator {

    private static final Pattern PLZ_PATTERN = Pattern.compile("\\d{5}");

    public static boolean validateInput(PraemienAntragRequest request) {
        return request.postleitzahl() != null
                && PLZ_PATTERN.matcher(request.postleitzahl()).matches()
                && request.kilometerleistung() > 0
                && request.fahrzeugtyp() != null;
    }

    public static Consumer<? super List<Location>> validateLocation(PraemienAntragRequest antrag) {
        final Location antragLocation = new Location(antrag.bundesland(), antrag.kreis(), antrag.stadt(), antrag.postleitzahl(), antrag.bezirk());
        return responseLocations -> {
            if (!responseLocations.contains(antragLocation)){
                throw new UnknownLocationException(antragLocation);
            }
        };
    }

    private PraemienAntragRequestValidator() {}
}
