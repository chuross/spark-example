package com.github.chuross.lgtm.controller;

import com.github.chuross.lgtm.ApplicationContext;
import com.github.chuross.lgtm.controller.delegator.Delegator;
import com.github.chuross.lgtm.controller.delegator.TopIndexDelegator;

public class TopController extends AbstractController {

    private static final Delegator<TopController> INDEX_DELEGATOR = new TopIndexDelegator();

    public TopController(final ApplicationContext context) {
        super(context);
    }

    @Override
    public void setup() {
        INDEX_DELEGATOR.delegate(this);
    }
}
