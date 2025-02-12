package com.github.blaszczyk.scopeviso.praemienservice.persistence;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Mono;

import java.util.UUID;

public interface PraemienAnfrageRepository extends ReactiveCrudRepository<PraemienAnfrageEntity, UUID> {
    Mono<PraemienAnfrageEntity> findByPraemienId(UUID praemienId);
}
