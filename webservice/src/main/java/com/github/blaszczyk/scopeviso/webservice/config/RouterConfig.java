package com.github.blaszczyk.scopeviso.webservice.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

import java.net.URI;

import static org.springframework.web.reactive.function.server.RequestPredicates.GET;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;
import static org.springframework.web.reactive.function.server.ServerResponse.ok;

@Configuration
public class RouterConfig {

    @Bean
    public RouteLocator routes(final RouteLocatorBuilder builder,
                               final PostcodeGatewayConfiguration postcodeGatewayConfig,
                               final PraemienGatewayConfiguration praemienGatewayConfig
    ) {
        return builder.routes()
                .route(predicateSpec -> predicateSpec
                        .path(postcodeGatewayConfig.getPathPattern())
                        .uri(postcodeGatewayConfig.getUri()))
                .route(predicateSpec -> predicateSpec
                        .path(praemienGatewayConfig.getPathPattern())
                        .uri(praemienGatewayConfig.getUri()))
                .build();
    }

    @Bean
    public RouterFunction<ServerResponse> indexRouter(@Value("classpath:/static/ui/index.html") final Resource indexHtml) {
        return route(GET("/ui/").or(GET("/ui/summary/*")), request ->
                ok()
                .contentType(MediaType.TEXT_HTML)
                .bodyValue(indexHtml)
        );
    }

    @Bean
    public RouterFunction<ServerResponse> redirectToUi() {
        return route(GET("/"), request ->
                ServerResponse.temporaryRedirect(URI.create("/ui/"))
                        .build());
    }
}
