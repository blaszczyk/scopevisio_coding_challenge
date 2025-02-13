package com.github.blaszczyk.scopeviso.praemienservice.persistence;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Mono;

import java.util.UUID;

public interface PraemienAntragRepository extends ReactiveCrudRepository<PraemienAntragEntity, UUID> {
    Mono<PraemienAntragEntity> findByPraemienId(UUID praemienId);
}
