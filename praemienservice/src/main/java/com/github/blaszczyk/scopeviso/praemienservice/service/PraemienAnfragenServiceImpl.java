package com.github.blaszczyk.scopeviso.praemienservice.service;

import com.github.blaszczyk.scopeviso.praemienservice.bi.PraemienCalculator;
import com.github.blaszczyk.scopeviso.praemienservice.client.PostcodeClient;
import com.github.blaszczyk.scopeviso.praemienservice.domain.Location;
import com.github.blaszczyk.scopeviso.praemienservice.domain.PraemienAnfrageRequest;
import com.github.blaszczyk.scopeviso.praemienservice.domain.UnknownLocationException;
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
    
    @Override
    public Mono<ResponseEntity<Integer>> fragePraemieAn(PraemienAnfrageRequest anfrage) {
        return postcodeClient.getLocations(anfrage.postleitzahl())
                .doOnNext(validateAnfrage(anfrage))
                .map(calculatePraemie(anfrage))
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

    private Function<Object, Integer> calculatePraemie(final PraemienAnfrageRequest anfrage) {
        return ignore -> PraemienCalculator.calculate(anfrage);
    }

}
