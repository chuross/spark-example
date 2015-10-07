package com.github.chuross.lgtm.model;

import com.github.chuross.lgtm.ApplicationContext;
import spark.Request;

import java.util.Map;

/**
 * リクエストの内容を見てViewに渡すデータを生成するためのクラス
 * DBやRedisなどの永続化層への参照はこのクラスが担当する
 */
public interface Model {

    Map<String, Object> getProperties(ApplicationContext context, Request request) throws Exception;
}
