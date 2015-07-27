package com.github.chuross.lgtm.delegator;

import com.github.chuross.lgtm.controller.Controller;
import com.github.chuross.lgtm.view.View;
import spark.TemplateEngine;

public interface TemplateDelegator<CONTROLLER extends Controller> extends Delegator<CONTROLLER> {

    View getView();

    TemplateEngine getTemplateEngine();
}
