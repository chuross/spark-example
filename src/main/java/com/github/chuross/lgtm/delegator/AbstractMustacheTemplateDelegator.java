package com.github.chuross.lgtm.delegator;

import com.github.chuross.lgtm.controller.Controller;
import com.github.chuross.lgtm.view.View;
import spark.TemplateEngine;
import spark.template.mustache.MustacheTemplateEngine;

public abstract class AbstractMustacheTemplateDelegator<CONTROLLER extends Controller, VIEW extends View> extends AbstractTemplateDelegator<CONTROLLER, VIEW> {

    @Override
    public TemplateEngine getTemplateEngine() {
        return new MustacheTemplateEngine();
    }
}
