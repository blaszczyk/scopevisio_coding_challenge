package com.github.blaszczyk.scopeviso.praemienservice.exception;

public class UnknownBundeslandException extends RuntimeException {
    public UnknownBundeslandException(final String bundesland) {
        super("Unknown bundesland: " + bundesland);
    }

}
