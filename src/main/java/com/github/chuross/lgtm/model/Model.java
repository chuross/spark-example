package com.github.chuross.lgtm.model;

import spark.Request;

import java.util.Map;

public interface Model {

    Map<String, Object> get(Request request) throws Exception;
}
