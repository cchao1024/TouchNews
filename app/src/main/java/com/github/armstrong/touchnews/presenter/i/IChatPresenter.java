package com.github.armstrong.touchnews.presenter.i;

/**
 * Created by cchao on 2016/4/25.
 * E-mail:   cchao1024@163.com
 * Description:
 */
public interface IChatPresenter {
        void onSendMessage(String message);
        void onReceive(String receive);
}
