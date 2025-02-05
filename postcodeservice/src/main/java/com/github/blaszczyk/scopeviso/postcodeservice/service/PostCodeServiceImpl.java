package com.github.blaszczyk.scopeviso.postcodeservice.service;

import com.github.blaszczyk.scopeviso.postcodeservice.domain.LocationTransformer;
import com.github.blaszczyk.scopeviso.postcodeservice.domain.Location;
import com.github.blaszczyk.scopeviso.postcodeservice.persistence.PostcodesRepository;
import com.github.blaszczyk.scopeviso.postcodeservice.validator.PostleitzahlValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import java.util.List;

@RestController
public class PostCodeServiceImpl implements PostCodeService {

    @Autowired
    private PostcodesRepository locationRepository;

    @Override
    public Mono<ResponseEntity<List<Location>>> getOrtsDaten(String plz) {
        if (PostleitzahlValidator.isValid(plz)){
            return locationRepository.findByPostleitzahl(plz)
                    .map(LocationTransformer::transform)
                    .collectList()
                    .map(ResponseEntity::ok);
        }
        else {
            return Mono.just(ResponseEntity.notFound().build());
        }
    }
}
