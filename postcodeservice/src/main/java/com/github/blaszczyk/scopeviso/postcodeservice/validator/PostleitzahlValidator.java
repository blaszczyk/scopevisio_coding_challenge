package com.github.blaszczyk.scopeviso.postcodeservice.validator;

import java.util.regex.Pattern;

public final class PostleitzahlValidator {

    private static final Pattern PLZ_PATTERN = Pattern.compile("\\d{5}");

    public static boolean isValid(final String postleitzahl) {
        return PLZ_PATTERN.matcher(postleitzahl).matches();
    }

    private PostleitzahlValidator() {}

}
