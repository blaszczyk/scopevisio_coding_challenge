package com.github.blaszczyk.scopeviso.postcodeservice.persistence;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Flux;

public interface PostcodesRepository extends ReactiveCrudRepository<Postcodes, String> {

    Flux<Postcodes> findByPostleitzahl(final String postleitzahl);
}
