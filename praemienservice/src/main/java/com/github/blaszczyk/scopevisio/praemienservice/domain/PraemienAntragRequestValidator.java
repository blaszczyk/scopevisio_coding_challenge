package com.github.blaszczyk.scopevisio.praemienservice.domain;

import com.github.blaszczyk.scopevisio.praemienservice.exception.UnknownLocationException;

import java.util.List;
import java.util.regex.Pattern;

public class PraemienAntragRequestValidator {

    private static final Pattern PLZ_PATTERN = Pattern.compile("\\d{5}");

    public static boolean isRequestValid(PraemienAntragRequest request) {
        return request.ort() != null
                && request.ort().postleitzahl() != null
                && PLZ_PATTERN.matcher(request.ort().postleitzahl()).matches()
                && request.kilometerleistung() > 0
                && request.fahrzeugtyp() != null;
    }

    public static void validateLocation(Location location, List<Location> validLocations) {
        if (!validLocations.contains(location)){
            throw new UnknownLocationException(location);
        }
    }

    private PraemienAntragRequestValidator() {}
}
