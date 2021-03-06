package com.github.chuross.lgtm;

import com.github.chuross.lgtm.controller.AbstractController;
import com.github.chuross.lgtm.controller.Controller;
import org.apache.commons.lang3.reflect.ConstructorUtils;
import org.reflections.Reflections;

/**
 * 対象パッケージのControllerを呼び出して振り分け処理を行うためのクラス
 */
public class Router {

    public void setup(ApplicationContext context) throws Exception {
        new Reflections(context.getControllerPackage())
                .getSubTypesOf(AbstractController.class)
                .stream()
                .map((controller) -> createController(context, controller))
                .forEach(Controller::setup);
    }

    private static Controller createController(ApplicationContext context, Class<? extends Controller> controller) {
        try {
            return ConstructorUtils.invokeConstructor(controller, context);
        } catch(Exception e) {
            throw new RuntimeException(String.format("Invalid Controller controller=%s", controller), e);
        }
    }
}
