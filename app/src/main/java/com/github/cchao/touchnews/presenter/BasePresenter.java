package com.github.cchao.touchnews.presenter;

/**
 * Created by cchao on 2016/8/18.
 * E-mail:   cchao1024@163.com
 * Description:
 */
public class BasePresenter <V> {
        V mView;
        public void attachView(V mvpView) {
                this.mView = mvpView;
        }

        public void detachView() {
                this.mView = null;
        }
}
