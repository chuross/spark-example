package com.github.chuross.lgtm.controller;

import com.github.chuross.lgtm.ApplicationContext;

public abstract class AbstractController implements Controller {

    private ApplicationContext context;

    public AbstractController(ApplicationContext context) {
        this.context = context;
    }

    @Override
    public ApplicationContext getApplicationContext() {
        return context;
    }

    @Override
    public String getPath() {
        return context.getPath(getClass());
    }
}
