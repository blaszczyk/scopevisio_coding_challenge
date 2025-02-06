package com.github.blaszczyk.scopeviso.praemienservice.domain;

public class UnknownLocationException extends RuntimeException {

    public UnknownLocationException(final Location location) {
        super("Unknown Location: " + location);
    }

}
