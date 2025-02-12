package com.github.blaszczyk.scopeviso.praemienservice.client;

import com.github.blaszczyk.scopeviso.praemienservice.domain.Location;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.List;

@Component
public final class PostcodeClient {

    @Value("${postcode-service.url}")
    private String postcodeServiceUrl;

    public Mono<List<Location>> getLocations(final String plz) {
        return WebClient.create(postcodeServiceUrl + plz)
                .get()
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .toEntityList(Location.class)
                .map(ResponseEntity::getBody);
    }
}
