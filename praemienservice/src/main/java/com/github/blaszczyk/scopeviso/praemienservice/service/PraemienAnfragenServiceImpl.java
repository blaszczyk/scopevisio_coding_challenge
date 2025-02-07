package com.github.blaszczyk.scopeviso.praemienservice.service;

import com.github.blaszczyk.scopeviso.praemienservice.bi.PraemienCalculator;
import com.github.blaszczyk.scopeviso.praemienservice.client.PostcodeClient;
import com.github.blaszczyk.scopeviso.praemienservice.domain.PraemienAnfrageRequest;
import com.github.blaszczyk.scopeviso.praemienservice.domain.PraemienAnfrageRequestValidator;
import com.github.blaszczyk.scopeviso.praemienservice.domain.PraemienAnfrageResponse;
import com.github.blaszczyk.scopeviso.praemienservice.persistence.PraemienAnfrage;
import com.github.blaszczyk.scopeviso.praemienservice.persistence.PraemienAnfrageRepository;
import com.github.blaszczyk.scopeviso.praemienservice.persistence.PraemienAnfrageTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import java.util.function.Function;

@RestController
public class PraemienAnfragenServiceImpl implements PraemienAnfragenService {

    @Autowired
    private PostcodeClient postcodeClient;

    @Autowired
    private PraemienAnfrageRepository praemienAnfragenRepository;
    
    @Override
    public Mono<ResponseEntity<PraemienAnfrageResponse>> postPraemienAnfrage(PraemienAnfrageRequest anfrage) {
        return postcodeClient.getLocations(anfrage.postleitzahl())
                .doOnNext(PraemienAnfrageRequestValidator.validate(anfrage))
                .map(ignore -> PraemienCalculator.calculate(anfrage))
                .flatMap(persist(anfrage))
                .map(ResponseEntity::ok);
    }

    private Function<? super Integer,? extends Mono<PraemienAnfrageResponse>> persist(PraemienAnfrageRequest anfrage) {
        return praemie -> {
            final PraemienAnfrage praemienAnfragen = PraemienAnfrageTransformer.transform(anfrage, praemie);
            return praemienAnfragenRepository.save(praemienAnfragen).map(a -> new PraemienAnfrageResponse(praemie, a.getId()));
        };
    }

}
