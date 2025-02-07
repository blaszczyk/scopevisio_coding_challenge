package com.github.blaszczyk.scopeviso.praemienservice.service;

import com.github.blaszczyk.scopeviso.praemienservice.domain.PraemienAnfrageRequest;
import com.github.blaszczyk.scopeviso.praemienservice.domain.PraemienAnfrageResponse;
import com.github.blaszczyk.scopeviso.praemienservice.domain.PraemienAnfrageSummary;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import java.util.UUID;

@RequestMapping("/praemien/api")
@ResponseBody
public interface PraemienAnfrageService {
    @PostMapping(path = "/anfrage",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    Mono<ResponseEntity<PraemienAnfrageResponse>> postPraemienAnfrage(@RequestBody final PraemienAnfrageRequest anfrage);

    @GetMapping(path = "/summary/{id}",
            produces = MediaType.APPLICATION_JSON_VALUE)
    Mono<ResponseEntity<PraemienAnfrageSummary>> getSummary(@PathVariable("id") final UUID id);

}
