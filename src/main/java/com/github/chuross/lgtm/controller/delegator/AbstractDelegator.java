package com.github.chuross.lgtm.controller.delegator;

import com.github.chuross.lgtm.Method;
import com.github.chuross.lgtm.controller.Controller;
import spark.Filter;
import spark.Spark;

public abstract class AbstractDelegator<CONTROLLER extends Controller> implements Delegator<CONTROLLER> {

    protected abstract Method getMethod();

    protected abstract void setUpGetRequest(CONTROLLER controller);

    protected abstract void setUpPostRequest(CONTROLLER controller);

    protected abstract void setUpPutRequest(CONTROLLER controller);

    protected abstract void setUpDeleteRequest(CONTROLLER controller);

    protected Filter getFilter(CONTROLLER controller) {
        return null;
    }

    @Override
    public void delegate(final CONTROLLER controller) {
        Filter filter = getFilter(controller);
        if(filter != null) {
            setUpFilter(controller, filter);
        }
        switch(getMethod()) {
            case GET:
                setUpGetRequest(controller);
                break;
            case POST:
                setUpPostRequest(controller);
                break;
            case PUT:
                setUpPutRequest(controller);
                break;
            case DELETE:
                setUpDeleteRequest(controller);
                break;
            default:
                throw new IllegalArgumentException("invalid method");
        }
    }

    private void setUpFilter(CONTROLLER controller, Filter filter) {
        Spark.before(controller.getPath(), ((request, response) -> {
            if(!request.requestMethod().equals(getMethod().name())) {
                return;
            }
            filter.handle(request, response);
        }));
    }
}
