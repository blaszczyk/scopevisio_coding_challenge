package com.github.blaszczyk.scopevisio.webservice.util;

import com.github.tomakehurst.wiremock.WireMockServer;
import org.springframework.test.util.TestSocketUtils;

import static com.github.tomakehurst.wiremock.client.WireMock.*;

public class MockService extends WireMockServer {

    public MockService() {
        super(TestSocketUtils.findAvailableTcpPort());
    }

    public void createGetExpectation(final String url, final int statusCode, final byte[] response) {
        stubFor(get(urlEqualTo(url))
                .willReturn(aResponse()
                        .withStatus(statusCode)
                        .withBody(response)
                )
        );
    }
}
