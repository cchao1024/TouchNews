package com.github.cchao.touchnews.presenter;

import com.github.cchao.touchnews.BaseApplication;
import com.github.cchao.touchnews.util.BaiDuApiService;

/**
 * Created by cchao on 2016/8/18.
 * E-mail:   cchao1024@163.com
 * Description: 根 Presenter 绑定View
 */
public abstract class BasePresenter < V > {
        BaiDuApiService mBaiDBaiDuApiService;
        V mView;

        public BasePresenter ( ) {
                mBaiDBaiDuApiService = BaseApplication.getAppComponent ( ).getBaiDuApiService ( );
        }

        public void bindView ( V mvpView ) {
                this.mView = mvpView;
        }

        public void unbindView ( ) {
                this.mView = null;
        }

}
