package com.github.cchao.touchnews.presenter;

import com.github.cchao.touchnews.BaseApplication;
import com.github.cchao.touchnews.di.component.AppComponent;

/**
 * Created by cchao on 2016/8/18.
 * E-mail:   cchao1024@163.com
 * Description:
 */
public abstract class BasePresenter < V > {
        V mView;

        public BasePresenter ( ) {
                setupActivityComponent ( BaseApplication.getAppComponent ( ) );
        }

        public void bindView ( V mvpView ) {
                this.mView = mvpView;
        }

        public void unbindView ( ) {
                this.mView = null;
        }

        /**
         * 刷新或初次加载数据
         */
        public void refreshData ( ) {}

        /**
         * 加载更多数据
         */
        public void loadMoreData ( ) {}

        /**
         * inject
         */
        protected abstract void setupActivityComponent ( AppComponent appComponent );
}
