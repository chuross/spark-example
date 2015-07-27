package com.github.chuross.lgtm;

import com.github.chuross.lgtm.view.View;

public class ModelAndView extends spark.ModelAndView {

    public ModelAndView(final Object model, final View view) {
        super(model, view.getName());
    }
}
