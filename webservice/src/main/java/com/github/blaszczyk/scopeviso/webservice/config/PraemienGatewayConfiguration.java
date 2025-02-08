package com.github.blaszczyk.scopeviso.webservice.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix="praemienservice")
public class PraemienGatewayConfiguration extends AbstractGatewayConfiguration {
}

