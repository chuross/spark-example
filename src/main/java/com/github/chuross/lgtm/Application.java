package com.github.chuross.lgtm;

import spark.Spark;

import java.net.HttpURLConnection;

public class Application {

    public static void main(String[] args) {
        Spark.exception(ApplicationException.class, ((exception, request, response) -> response.status(HttpURLConnection.HTTP_INTERNAL_ERROR)));
        try {
            new Router().setup(new ApplicationContext());
        } catch(Exception e) {
            throw new IllegalStateException("Routes setup failed", e);
            // TODO Dump to log
        }
    }
}
