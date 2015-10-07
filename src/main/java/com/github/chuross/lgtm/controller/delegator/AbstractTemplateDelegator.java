package com.github.chuross.lgtm.controller.delegator;

import com.github.chuross.lgtm.ApplicationException;
import com.github.chuross.lgtm.Method;
import com.github.chuross.lgtm.ModelAndView;
import com.github.chuross.lgtm.controller.Controller;
import com.github.chuross.lgtm.controller.Result;
import spark.*;
import spark.template.mustache.MustacheTemplateEngine;

public abstract class AbstractTemplateDelegator<CONTROLLER extends Controller> implements TemplateDelegator<CONTROLLER> {

    protected abstract Method getMethod();

    protected abstract Result getResult(CONTROLLER controller, Request request);

    protected Filter getFilter(CONTROLLER controller) {
        return null;
    }

    @Override
    public void delegate(final CONTROLLER controller) {
        Filter filter = getFilter(controller);
        if(filter != null) {
            Spark.before(controller.getPath(), ((request, response) -> {
                if(!request.requestMethod().equals(getMethod().name())) {
                    return;
                }
                filter.handle(request, response);
            }));
        }
        switch(getMethod()) {
            case GET:
                Spark.get(controller.getPath(), (request, response) -> getModelAndView(controller, request, response), getTemplateEngine());
                break;
            case POST:
                Spark.post(controller.getPath(), (request, response) -> getModelAndView(controller, request, response), getTemplateEngine());
                break;
            case PUT:
                Spark.put(controller.getPath(), (request, response) -> getModelAndView(controller, request, response), getTemplateEngine());
                break;
            case DELETE:
                Spark.delete(controller.getPath(), (request, response) -> getModelAndView(controller, request, response), getTemplateEngine());
                break;
            default:
                throw new IllegalArgumentException("invalid method");
        }
    }

    private ModelAndView getModelAndView(CONTROLLER controller, Request request, Response response) throws ApplicationException {
        try {
            Result result = getResult(controller, request);
            if(result == null) {
                throw new IllegalStateException("result is null");
            }
            response.status(result.getStatus());
            return new ModelAndView(result.getProperties(), getView());
        } catch(Exception e) {
            throw new ApplicationException(e);
        }
    }

    @Override
    public TemplateEngine getTemplateEngine() {
        return new MustacheTemplateEngine();
    }
}
