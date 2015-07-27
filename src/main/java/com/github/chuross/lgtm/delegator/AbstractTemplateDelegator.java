package com.github.chuross.lgtm.delegator;

import com.github.chuross.lgtm.ApplicationException;
import com.github.chuross.lgtm.Method;
import com.github.chuross.lgtm.ModelAndView;
import com.github.chuross.lgtm.controller.Controller;
import com.github.chuross.lgtm.view.View;
import spark.Request;
import spark.Response;
import spark.Spark;

import java.util.Map;

public abstract class AbstractTemplateDelegator<CONTROLLER extends Controller, VIEW extends View> implements TemplateDelegator<CONTROLLER, VIEW> {

    protected abstract Method getMethod();

    protected abstract Map<String, Object> getModel(Request request, Response response);

    @Override
    public void delegate(final CONTROLLER controller) {
        switch(getMethod()) {
            case GET:
                Spark.get(controller.getPath(), this::getModelAndView, getTemplateEngine());
                break;
            case POST:
                Spark.post(controller.getPath(), this::getModelAndView, getTemplateEngine());
                break;
            case PUT:
                Spark.put(controller.getPath(), this::getModelAndView, getTemplateEngine());
                break;
            case DELETE:
                Spark.delete(controller.getPath(), this::getModelAndView, getTemplateEngine());
                break;
            default:
                throw new IllegalArgumentException("invalid method");
        }
    }

    private ModelAndView getModelAndView(Request request, Response response) throws ApplicationException {
        try {
            return new ModelAndView(getModel(request, response), getView());
        } catch(Exception e) {
            throw new ApplicationException(e);
        }
    }
}
