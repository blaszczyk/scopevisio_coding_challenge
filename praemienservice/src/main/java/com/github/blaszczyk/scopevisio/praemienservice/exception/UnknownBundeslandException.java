package com.github.blaszczyk.scopevisio.praemienservice.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class UnknownBundeslandException extends ResponseStatusException {
    public UnknownBundeslandException(final String bundesland) {
        super(HttpStatus.BAD_REQUEST, "Unknown bundesland: " + bundesland);
    }
}
