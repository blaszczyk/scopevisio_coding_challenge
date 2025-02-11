package com.github.blaszczyk.scopeviso.praemienservice.util;

import org.testcontainers.containers.PostgreSQLContainer;

public class DatabaseContainer extends PostgreSQLContainer<DatabaseContainer> {
    private static final String POSTGRES_IMAGE = "postgres";

    public DatabaseContainer() {
        super(POSTGRES_IMAGE);
        withLogConsumer(outputFrame -> System.out.println("DATABASE: + " + outputFrame.getUtf8String().trim()));
    }
}
