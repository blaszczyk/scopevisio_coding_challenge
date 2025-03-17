package com.github.blaszczyk.scopevisio.praemienservice.persistence;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;

import java.util.UUID;

public interface PraemienAntragRepository extends ReactiveCrudRepository<PraemienAntragEntity, UUID> {
}
