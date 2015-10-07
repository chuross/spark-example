package com.github.chuross.lgtm.delegator;

import com.github.chuross.lgtm.Method;
import com.github.chuross.lgtm.controller.TopController;
import com.github.chuross.lgtm.view.View;
import spark.Request;

import java.util.Map;

public class TopIndexDelegator extends AbstractMustacheTemplateDelegator<TopController> {

    @Override
    public View getView() {
        return new View("top/index.mustache");
    }

    @Override
    protected Method getMethod() {
        return Method.GET;
    }

    @Override
    protected Map<String, Object> getModel(TopController controller, final Request request) {
        return null;
    }
}
