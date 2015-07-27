package com.github.chuross.lgtm.delegator;

import com.github.chuross.lgtm.controller.Controller;

/**
 * Controllerのリクエストに対する具体的な処理を担当する
 * ModelとViewのバインドやhaltのタイミングもこのクラスが担当する
 */
public interface Delegator<CONTROLLER extends Controller> {

    void delegate(CONTROLLER controller);
}
