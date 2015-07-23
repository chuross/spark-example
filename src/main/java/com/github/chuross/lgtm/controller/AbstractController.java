package com.github.chuross.lgtm.controller;

import com.github.chuross.lgtm.ApplicationContext;

public abstract class AbstractController implements Controller {

    private ApplicationContext context;

    public AbstractController(ApplicationContext context) {
        this.context = context;
    }

    protected ApplicationContext getContext() {
        return context;
    }

    @Override
    public String getPath() {
        return context.getPath(getClass());
    }
}
