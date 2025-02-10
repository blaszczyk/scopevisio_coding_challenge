package com.github.blaszczyk.scopeviso.praemienservice.exception;

import com.github.blaszczyk.scopeviso.praemienservice.domain.Location;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.web.server.ResponseStatusException;

public class UnknownLocationException extends ResponseStatusException {

    public UnknownLocationException(final Location location) {
        super(HttpStatus.BAD_REQUEST, "Unknown Location: " + location);
    }

}
