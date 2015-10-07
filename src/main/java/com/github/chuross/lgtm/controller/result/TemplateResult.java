package com.github.chuross.lgtm.controller.result;

import java.util.Map;

public class TemplateResult extends AbstractResult {

    private Map<String, Object> properties;

    public TemplateResult() {
    }

    public TemplateResult(int status) {
        this(status, null, null);
    }

    public TemplateResult(final int status, final Map<String, String> headers, final Map<String, Object> properties) {
        super(status, "text/html", headers);
        this.properties = properties;
    }

    public Map<String, Object> getProperties() {
        return properties;
    }
}
