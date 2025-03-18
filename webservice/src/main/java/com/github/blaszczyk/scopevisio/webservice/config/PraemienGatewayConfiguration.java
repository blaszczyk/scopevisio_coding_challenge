package com.github.blaszczyk.scopevisio.webservice.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix="praemien-service")
public class PraemienGatewayConfiguration extends AbstractGatewayConfiguration {
}

