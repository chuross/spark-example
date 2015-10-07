package com.github.chuross.lgtm.controller;

import com.github.chuross.lgtm.ApplicationContext;

/**
 * 期待されたリクエストを受けて適切な処理に振り分けるクラス
 * 実際にリクエストメソッドごとで行う処理はDelegaterに全て任せる
 */
public interface Controller {

    void setup();

    ApplicationContext getApplicationContext();

    String getPath();
}
