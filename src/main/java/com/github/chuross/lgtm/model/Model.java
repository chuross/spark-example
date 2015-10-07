package com.github.chuross.lgtm.model;

import com.github.chuross.lgtm.ApplicationContext;
import spark.Request;

import java.util.Map;

/**
 * リクエストの内容を見てViewに渡すデータを生成するためのクラス
 */
public interface Model {

    Map<String, Object> get(ApplicationContext context, Request request) throws Exception;
}
