package com.github.chuross.lgtm.controller.delegator;

import com.github.chuross.lgtm.ApplicationException;
import com.github.chuross.lgtm.Method;
import com.github.chuross.lgtm.ModelAndView;
import com.github.chuross.lgtm.controller.Controller;
import spark.Filter;
import spark.Request;
import spark.Spark;

import java.util.Map;

public abstract class AbstractTemplateDelegator<CONTROLLER extends Controller> implements TemplateDelegator<CONTROLLER> {

    protected abstract Method getMethod();

    protected abstract Map<String, Object> getModel(CONTROLLER controller, Request request);

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
                Spark.get(controller.getPath(), (request, response) -> getModelAndView(controller, request), getTemplateEngine());
                break;
            case POST:
                Spark.post(controller.getPath(), (request, response) -> getModelAndView(controller, request), getTemplateEngine());
                break;
            case PUT:
                Spark.put(controller.getPath(), (request, response) -> getModelAndView(controller, request), getTemplateEngine());
                break;
            case DELETE:
                Spark.delete(controller.getPath(), (request, response) -> getModelAndView(controller, request), getTemplateEngine());
                break;
            default:
                throw new IllegalArgumentException("invalid method");
        }
    }

    private ModelAndView getModelAndView(CONTROLLER controller, Request request) throws ApplicationException {
        try {
            return new ModelAndView(getModel(controller, request), getView());
        } catch(Exception e) {
            throw new ApplicationException(e);
        }
    }
}
