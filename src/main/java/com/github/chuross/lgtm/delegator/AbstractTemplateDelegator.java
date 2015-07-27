package com.github.chuross.lgtm.delegator;

import com.github.chuross.lgtm.ApplicationException;
import com.github.chuross.lgtm.Method;
import com.github.chuross.lgtm.ModelAndView;
import com.github.chuross.lgtm.controller.Controller;
import com.github.chuross.lgtm.view.View;
import spark.Filter;
import spark.Request;
import spark.Response;
import spark.Spark;

import java.util.Map;

public abstract class AbstractTemplateDelegator<CONTROLLER extends Controller, VIEW extends View> implements TemplateDelegator<CONTROLLER, VIEW> {

    protected Filter getFilter() {
        return null;
    }

    protected abstract Method getMethod();

    protected abstract Map<String, Object> getModel(CONTROLLER controller, Request request, Response response);

    @Override
    public void delegate(final CONTROLLER controller) {
        if(getFilter() != null) {
            Spark.before(controller.getPath(), ((request, response) -> {
                if(!request.requestMethod().equals(getMethod().name())) {
                    return;
                }
                getFilter().handle(request, response);
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
            return new ModelAndView(getModel(controller, request, response), getView());
        } catch(Exception e) {
            throw new ApplicationException(e);
        }
    }
}
