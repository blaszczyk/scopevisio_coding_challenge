package com.github.blaszczyk.scopeviso.postcodeservice.persistence;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Flux;

public interface PostcodeRepository extends ReactiveCrudRepository<PostcodeEntity, String> {

    Flux<PostcodeEntity> findByPostleitzahl(final String postleitzahl);
}
