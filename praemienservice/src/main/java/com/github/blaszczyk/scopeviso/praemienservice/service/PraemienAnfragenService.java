package com.github.blaszczyk.scopeviso.praemienservice.service;

import com.github.blaszczyk.scopeviso.praemienservice.domain.PraemienAnfrageRequest;
import com.github.blaszczyk.scopeviso.praemienservice.domain.PraemienAnfrageResponse;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@RequestMapping("/praemien/api")
@ResponseBody
public interface PraemienAnfragenService {
    @PostMapping(path = "/anfrage",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    Mono<ResponseEntity<PraemienAnfrageResponse>> fragePraemieAn(@RequestBody final PraemienAnfrageRequest anfrage);
}
