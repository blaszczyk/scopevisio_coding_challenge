package com.github.blaszczyk.scopeviso.webservice.config;

public abstract class AbstractGatewayConfiguration {
    private String host;
    private String port;
    private String path;

    public String getPathPattern() {
        return path + "**";
    }

    public String getUri() {
        return "http://" + host + ":" + port + path;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public String getPort() {
        return port;
    }

    public void setPort(String port) {
        this.port = port;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }
}
