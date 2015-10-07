package com.github.chuross.lgtm.controller;

import java.util.Map;

/**
 * リクエストした結果の情報を保持するクラス
 */
public interface Result {

    int getStatus();

    Map<String, Object> getProperties();
}
