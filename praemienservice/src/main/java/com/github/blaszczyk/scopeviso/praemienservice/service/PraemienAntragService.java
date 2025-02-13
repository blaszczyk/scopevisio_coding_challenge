package com.github.blaszczyk.scopeviso.praemienservice.service;

import com.github.blaszczyk.scopeviso.praemienservice.domain.PraemienAntragRequest;
import com.github.blaszczyk.scopeviso.praemienservice.domain.PraemienAntragResponse;
import com.github.blaszczyk.scopeviso.praemienservice.domain.PraemienAntragSummary;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import java.util.UUID;

@RequestMapping("/praemien/api")
@ResponseBody
public interface PraemienAntragService {
    @PostMapping(path = "/antrag",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    Mono<ResponseEntity<PraemienAntragResponse>> postPraemienAntrag(@RequestBody final PraemienAntragRequest antrag);

    @GetMapping(path = "/summary/{id}",
            produces = MediaType.APPLICATION_JSON_VALUE)
    Mono<ResponseEntity<PraemienAntragSummary>> getSummary(@PathVariable("id") final UUID id);

}
