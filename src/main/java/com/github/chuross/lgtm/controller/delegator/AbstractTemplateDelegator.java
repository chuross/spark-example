package com.github.chuross.lgtm.controller.delegator;

import com.github.chuross.lgtm.ApplicationException;
import com.github.chuross.lgtm.ModelAndView;
import com.github.chuross.lgtm.controller.Controller;
import com.github.chuross.lgtm.controller.result.Result;
import com.github.chuross.lgtm.controller.result.TemplateResult;
import spark.Request;
import spark.Response;
import spark.Spark;
import spark.TemplateEngine;
import spark.template.mustache.MustacheTemplateEngine;

import java.util.Map;

public abstract class AbstractTemplateDelegator<CONTROLLER extends Controller> extends AbstractDelegator<CONTROLLER> implements TemplateDelegator<CONTROLLER> {

    protected abstract TemplateResult getResult(CONTROLLER controller, Request request);

    @Override
    protected void setUpGetRequest(final CONTROLLER controller) {
        Spark.get(controller.getPath(), (request, response) -> getModelAndView(controller, request, response), getTemplateEngine());
    }

    @Override
    protected void setUpPostRequest(final CONTROLLER controller) {
        Spark.post(controller.getPath(), (request, response) -> getModelAndView(controller, request, response), getTemplateEngine());
    }

    @Override
    protected void setUpPutRequest(final CONTROLLER controller) {
        Spark.put(controller.getPath(), (request, response) -> getModelAndView(controller, request, response), getTemplateEngine());
    }

    @Override
    protected void setUpDeleteRequest(final CONTROLLER controller) {
        Spark.delete(controller.getPath(), (request, response) -> getModelAndView(controller, request, response), getTemplateEngine());
    }

    // TODO status, header, contentTypeのセットをベースクラスにやらせる
    private ModelAndView getModelAndView(CONTROLLER controller, Request request, Response response) throws ApplicationException {
        try {
            TemplateResult result = getResult(controller, request);
            if(result == null) {
                throw new IllegalStateException("result is null");
            }
            response.status(result.getStatus());
            response.type(result.getContentType());
            addAllHeaders(response, result);
            return new ModelAndView(result.getProperties(), getView());
        } catch(Exception e) {
            throw new ApplicationException(e);
        }
    }

    private static void addAllHeaders(Response response, Result result) {
        if(result.getHeaders() == null) {
            return;
        }
        for(Map.Entry<String, String> entry : result.getHeaders().entrySet()) {
            response.header(entry.getKey(), entry.getValue());
        }
    }

    @Override
    public TemplateEngine getTemplateEngine() {
        return new MustacheTemplateEngine();
    }
}
