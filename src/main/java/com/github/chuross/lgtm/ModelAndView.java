package com.github.chuross.lgtm;

import com.github.chuross.lgtm.view.View;

import java.util.Map;

public class ModelAndView extends spark.ModelAndView {

    public ModelAndView(final Map<String, Object> model, final View view) {
        super(model, view.getName());
    }
}
