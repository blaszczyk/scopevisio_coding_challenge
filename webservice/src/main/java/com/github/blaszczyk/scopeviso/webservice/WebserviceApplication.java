package com.github.blaszczyk.scopeviso.webservice;

import com.github.blaszczyk.scopeviso.webservice.config.PostcodeGatewayConfiguration;
import com.github.blaszczyk.scopeviso.webservice.config.PraemienGatewayConfiguration;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class WebserviceApplication {

	public static void main(String[] args) {
		SpringApplication.run(WebserviceApplication.class, args);
	}

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

}
