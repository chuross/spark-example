package com.github.chuross.lgtm.controller;

import com.github.chuross.lgtm.ApplicationContext;
import com.github.chuross.lgtm.delegator.TopIndexDelegator;

public class TopController extends AbstractController {

    public TopController(final ApplicationContext context) {
        super(context);
    }

    @Override
    public void setup() {
        new TopIndexDelegator().delegate(this);
    }
}
