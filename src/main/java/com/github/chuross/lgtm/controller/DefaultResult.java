package com.github.chuross.lgtm.controller;

import java.net.HttpURLConnection;
import java.util.Map;

public class DefaultResult implements Result {

    private int status;
    private Map<String, Object> properties;

    public DefaultResult() {
        this(HttpURLConnection.HTTP_OK);
    }

    public DefaultResult(int status) {
        this(status, null);
    }

    public DefaultResult(Map<String, Object> properties) {
        this(HttpURLConnection.HTTP_OK, properties);
    }

    public DefaultResult(int status, Map<String, Object> properties) {
        this.status = status;
        this.properties = properties;
    }

    @Override
    public int getStatus() {
        return status;
    }

    @Override
    public Map<String, Object> getProperties() {
        return properties;
    }
}
