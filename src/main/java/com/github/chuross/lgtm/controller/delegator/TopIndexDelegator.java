package com.github.chuross.lgtm.controller.delegator;

import com.github.chuross.lgtm.Method;
import com.github.chuross.lgtm.controller.DefaultResult;
import com.github.chuross.lgtm.controller.Result;
import com.github.chuross.lgtm.controller.TopController;
import com.github.chuross.lgtm.view.View;
import spark.Request;

public class TopIndexDelegator extends AbstractTemplateDelegator<TopController> {

    // private TopIndexModel model = new TopIndexModel();

    @Override
    public View getView() {
        return new View("top/index.mustache");
    }

    @Override
    protected Method getMethod() {
        return Method.GET;
    }

    @Override
    protected Result getResult(final TopController controller, final Request request) {
        // new DefaultResult(model.getProperties());
        return new DefaultResult();
    }
}
