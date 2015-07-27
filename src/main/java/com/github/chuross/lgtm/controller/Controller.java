package com.github.chuross.lgtm.controller;

import com.github.chuross.lgtm.ApplicationContext;

public interface Controller {

    void setup();

    ApplicationContext getApplicationContext();

    String getPath();
}
