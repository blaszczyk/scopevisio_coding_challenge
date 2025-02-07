package com.github.blaszczyk.scopeviso.praemienservice.persistence;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;

import java.util.UUID;

public interface PraemienAnfrageRepository extends ReactiveCrudRepository<PraemienAnfrage, UUID> {
}
