package com.github.blaszczyk.scopeviso.praemienservice.service;

import com.github.blaszczyk.scopeviso.praemienservice.bi.PraemienCalculator;
import com.github.blaszczyk.scopeviso.praemienservice.client.PostcodeClient;
import com.github.blaszczyk.scopeviso.praemienservice.domain.PraemienAnfrageRequest;
import com.github.blaszczyk.scopeviso.praemienservice.domain.PraemienAnfrageRequestValidator;
import com.github.blaszczyk.scopeviso.praemienservice.domain.PraemienAnfrageResponse;
import com.github.blaszczyk.scopeviso.praemienservice.domain.PraemienAnfrageSummary;
import com.github.blaszczyk.scopeviso.praemienservice.persistence.PraemienAnfrage;
import com.github.blaszczyk.scopeviso.praemienservice.persistence.PraemienAnfrageRepository;
import com.github.blaszczyk.scopeviso.praemienservice.persistence.PraemienAnfrageTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import java.util.UUID;
import java.util.function.Function;

@RestController
public class PraemienAnfrageServiceImpl implements PraemienAnfrageService {

    @Autowired
    private PostcodeClient postcodeClient;

    @Autowired
    private PraemienAnfrageRepository praemienAnfrageRepository;
    
    @Override
    public Mono<ResponseEntity<PraemienAnfrageResponse>> postPraemienAnfrage(PraemienAnfrageRequest anfrage) {
        return postcodeClient.getLocations(anfrage.postleitzahl())
                .doOnNext(PraemienAnfrageRequestValidator.validate(anfrage))
                .map(ignore -> PraemienCalculator.calculate(anfrage))
                .flatMap(persist(anfrage))
                .map(ResponseEntity::ok)
                .onErrorResume(e -> {
                    e.printStackTrace();
                    return Mono.just(ResponseEntity.badRequest().build());
                });
    }

    @Override
    public Mono<ResponseEntity<PraemienAnfrageSummary>> getSummary(UUID id) {
        return praemienAnfrageRepository.findByPraemienId(id)
                .map(PraemienAnfrageTransformer::transformToSummary)
                .map(ResponseEntity::ok);
    }

    private Function<? super Integer,? extends Mono<PraemienAnfrageResponse>> persist(PraemienAnfrageRequest request) {
        return praemie -> {
            final PraemienAnfrage praemienAnfrage = PraemienAnfrageTransformer.transform(request, praemie);
            return praemienAnfrageRepository.save(praemienAnfrage)
                    .map(anfrage -> new PraemienAnfrageResponse(praemie, anfrage.getPraemienId()));
        };
    }

}
