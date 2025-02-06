package com.github.blaszczyk.scopeviso.praemienservice.service;

import com.github.blaszczyk.scopeviso.praemienservice.bi.PraemienCalculator;
import com.github.blaszczyk.scopeviso.praemienservice.client.PostcodeClient;
import com.github.blaszczyk.scopeviso.praemienservice.domain.Location;
import com.github.blaszczyk.scopeviso.praemienservice.domain.PraemienAnfrageRequest;
import com.github.blaszczyk.scopeviso.praemienservice.domain.PraemienAnfrageResponse;
import com.github.blaszczyk.scopeviso.praemienservice.exception.UnknownLocationException;
import com.github.blaszczyk.scopeviso.praemienservice.persistence.Praemienanfragen;
import com.github.blaszczyk.scopeviso.praemienservice.persistence.PraemienanfragenRepository;
import com.github.blaszczyk.scopeviso.praemienservice.persistence.PraemienanfragenTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;

@RestController
public class PraemienAnfragenServiceImpl implements PraemienAnfragenService {

    @Autowired
    private PostcodeClient postcodeClient;

    @Autowired
    private PraemienanfragenRepository praemienAnfragenRepository;
    
    @Override
    public Mono<ResponseEntity<PraemienAnfrageResponse>> fragePraemieAn(PraemienAnfrageRequest anfrage) {
        return postcodeClient.getLocations(anfrage.postleitzahl())
                .doOnNext(validateAnfrage(anfrage))
                .map(ignore -> PraemienCalculator.calculate(anfrage))
                .flatMap(persist(anfrage))
                .map(ResponseEntity::ok);
    }

    private Consumer<? super List<Location>> validateAnfrage(PraemienAnfrageRequest anfrage) {
        final Location anfrageLocation = new Location(anfrage.bundesland(), anfrage.kreis(), anfrage.stadt(), anfrage.postleitzahl(), anfrage.bezirk());
        return responseLocations -> {
          if (!responseLocations.contains(anfrageLocation)){
              throw new UnknownLocationException(anfrageLocation);
          }
        };
    }

    private Function<? super Integer,? extends Mono<PraemienAnfrageResponse>> persist(PraemienAnfrageRequest anfrage) {
        return praemie -> {
            final Praemienanfragen praemienAnfragen = PraemienanfragenTransformer.transform(anfrage, praemie);
            return praemienAnfragenRepository.save(praemienAnfragen).map(a -> new PraemienAnfrageResponse(praemie, a.getId()));
        };
    }

}
