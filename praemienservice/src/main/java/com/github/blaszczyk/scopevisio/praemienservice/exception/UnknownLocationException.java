package com.github.blaszczyk.scopevisio.praemienservice.exception;

import com.github.blaszczyk.scopevisio.praemienservice.domain.Location;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class UnknownLocationException extends ResponseStatusException {

    public UnknownLocationException(final Location location) {
        super(HttpStatus.BAD_REQUEST, "Unknown Location: " + location);
    }

}
