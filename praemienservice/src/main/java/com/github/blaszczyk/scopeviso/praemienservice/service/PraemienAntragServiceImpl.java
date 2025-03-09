package com.github.blaszczyk.scopeviso.praemienservice.service;

import com.github.blaszczyk.scopeviso.praemienservice.bi.PraemienCalculator;
import com.github.blaszczyk.scopeviso.praemienservice.client.PostcodeClient;
import com.github.blaszczyk.scopeviso.praemienservice.domain.PraemienAntragRequest;
import com.github.blaszczyk.scopeviso.praemienservice.domain.PraemienAntragRequestValidator;
import com.github.blaszczyk.scopeviso.praemienservice.domain.PraemienAntragResponse;
import com.github.blaszczyk.scopeviso.praemienservice.domain.PraemienAntragSummary;
import com.github.blaszczyk.scopeviso.praemienservice.persistence.PraemienAntragRepository;
import com.github.blaszczyk.scopeviso.praemienservice.persistence.PraemienAntragTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import reactor.core.publisher.Mono;

import java.util.UUID;

@RestController
public class PraemienAntragServiceImpl implements PraemienAntragService {

    @Autowired
    private PostcodeClient postcodeClient;

    @Autowired
    private PraemienAntragRepository praemienAntragRepository;
    
    @Override
    public Mono<ResponseEntity<PraemienAntragResponse>> postPraemienAntrag(PraemienAntragRequest antrag) {
        if(PraemienAntragRequestValidator.isRequestValid(antrag)) {
            return postcodeClient.getLocations(antrag.ort().postleitzahl())
                    .doOnNext(PraemienAntragRequestValidator.validateLocation(antrag.ort()))
                    .map(ignore -> PraemienCalculator.calculate(antrag))
                    .map(praemie -> PraemienAntragTransformer.transformToEntity(antrag, praemie))
                    .flatMap(praemienAntragRepository::save)
                    .map(PraemienAntragTransformer::transformToResponse)
                    .map(ResponseEntity::ok)
                    .onErrorResume(this::handleError);
        }
        else {
            return Mono.just(ResponseEntity.badRequest().build());
        }
    }

    @Override
    public Mono<ResponseEntity<PraemienAntragSummary>> getSummary(UUID id) {
        return praemienAntragRepository.findById(id)
                .map(PraemienAntragTransformer::transformToSummary)
                .map(ResponseEntity::ok)
                .switchIfEmpty(Mono.just(ResponseEntity.badRequest().build()));
    }

    private Mono<ResponseEntity<PraemienAntragResponse>> handleError(Throwable error) {
        final HttpStatusCode status;
        if (error instanceof ResponseStatusException responseStatusException) {
            status = responseStatusException.getStatusCode();
        }
        else {
            status = HttpStatus.INTERNAL_SERVER_ERROR;
        }
        return Mono.just(ResponseEntity.status(status).build());
    }

}
