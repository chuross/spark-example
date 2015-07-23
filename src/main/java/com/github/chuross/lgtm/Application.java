package com.github.chuross.lgtm;

public class Application {

    public static void main(String[] args) {
        try {
            new Router().setup(new ApplicationContext());
        } catch(Exception e) {
            throw new IllegalStateException("Routes setup failed", e);
            // TODO Dump to log
        }
    }
}
