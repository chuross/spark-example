package com.github.chuross.lgtm.controller.result;

import java.net.HttpURLConnection;
import java.util.Map;

public abstract class AbstractResult implements Result {

    private int status;
    private String contentType;
    private Map<String, String> headers;

    public AbstractResult() {
        this(HttpURLConnection.HTTP_OK, null, null);
    }

    public AbstractResult(int status) {
        this(status, null, null);
    }

    public AbstractResult(int status, String contentType) {
        this(status, contentType, null);
    }

    public AbstractResult(int status, String contentType, Map<String, String> headers) {
        this.status = status;
        this.contentType = contentType;
        this.headers = headers;
    }

    @Override
    public int getStatus() {
        return status;
    }

    @Override
    public String getContentType() {
        return contentType;
    }

    @Override
    public Map<String, String> getHeaders() {
        return headers;
    }
}
