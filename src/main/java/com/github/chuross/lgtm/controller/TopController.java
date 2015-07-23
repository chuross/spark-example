package com.github.chuross.lgtm.controller;

import com.github.chuross.lgtm.ApplicationContext;
import spark.Spark;

public class TopController extends AbstractController {

    public TopController(final ApplicationContext context) {
        super(context);
    }

    @Override
    public void setupRoutes() {
        Spark.get(getPath(), ((request, response) -> "test"));
    }
}
