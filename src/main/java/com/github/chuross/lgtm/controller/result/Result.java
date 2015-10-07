package com.github.chuross.lgtm.controller.result;

import java.util.Map;

/**
 * リクエストした結果の情報を保持するクラス
 */
public interface Result {

    int getStatus();

    String getContentType();

    Map<String, String> getHeaders();
}
