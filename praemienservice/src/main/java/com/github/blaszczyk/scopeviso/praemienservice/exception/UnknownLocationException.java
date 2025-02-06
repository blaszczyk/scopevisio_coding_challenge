package com.github.blaszczyk.scopeviso.praemienservice.exception;

import com.github.blaszczyk.scopeviso.praemienservice.domain.Location;

public class UnknownLocationException extends RuntimeException {

    public UnknownLocationException(final Location location) {
        super("Unknown Location: " + location);
    }

}
