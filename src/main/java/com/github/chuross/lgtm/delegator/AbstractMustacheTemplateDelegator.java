package com.github.chuross.lgtm.delegator;

import com.github.chuross.lgtm.controller.Controller;
import spark.TemplateEngine;
import spark.template.mustache.MustacheTemplateEngine;

public abstract class AbstractMustacheTemplateDelegator<CONTROLLER extends Controller> extends AbstractTemplateDelegator<CONTROLLER> {

    @Override
    public TemplateEngine getTemplateEngine() {
        return new MustacheTemplateEngine();
    }
}
