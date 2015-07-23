package com.github.chuross.lgtm;

import com.github.chuross.lgtm.controller.Controller;
import com.google.common.base.Charsets;
import com.google.common.collect.Maps;
import org.apache.commons.io.IOUtils;
import org.yaml.snakeyaml.Yaml;

import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.Map;

public class ApplicationContext {

    private Map<Class<? extends Controller>, String> controllerMap = Maps.newHashMap();

    @SuppressWarnings("unchecked")
    public ApplicationContext() throws IOException {
        readUrlMap().forEach((key, value) -> {
            try {
                controllerMap.put(getControllerClass(key.toString()), value.toString());
            } catch(ClassNotFoundException e) {
                throw new IllegalStateException(String.format("Invalid Controller name called key=%s", key));
            }
        });
    }

    @SuppressWarnings("unchecked")
    private Class<? extends Controller> getControllerClass(String name) throws ClassNotFoundException {
        return (Class<? extends Controller>) Class.forName(String.format("%s.%s", getControllerPackage(), name));
    }

    private Map readUrlMap() throws IOException {
        InputStream inputStream = null;
        try {
            inputStream = getResourcesAsInputStream("/url_mapping.yaml");
            return (Map) new Yaml().load(inputStream);
        } finally {
            IOUtils.closeQuietly(inputStream);
        }
    }

    public String getControllerPackage() {
        return Controller.class.getPackage().getName();
    }

    public Date now() {
        return new Date();
    }

    public String getResourceAsString(String path) throws IOException {
        InputStream inputStream = null;
        try {
            inputStream = getResourcesAsInputStream(path);
            return IOUtils.toString(inputStream, Charsets.UTF_8);
        } finally {
            IOUtils.closeQuietly(inputStream);
        }
    }

    public InputStream getResourcesAsInputStream(String path) {
        return getClass().getResourceAsStream(path);
    }

    public String getPath(Class<? extends Controller> controllerClass) {
        if(!controllerMap.containsKey(controllerClass)) {
            throw new IllegalStateException(String.format("not exists in url_mapping.yaml controller=%s", controllerClass));
        }
        return controllerMap.get(controllerClass);
    }
}
