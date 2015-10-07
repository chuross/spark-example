package com.github.chuross.lgtm.controller.delegator;

import com.github.chuross.lgtm.Method;
import com.github.chuross.lgtm.controller.TopController;
import com.github.chuross.lgtm.controller.result.TemplateResult;
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
    protected TemplateResult getResult(final TopController controller, final Request request) {
        // new TemplateResult(200, null, model.getProperties());
        return new TemplateResult();
    }
}
