package com.github.cchao.touchnews.di.component;

import com.github.cchao.touchnews.di.module.*;
import com.github.cchao.touchnews.presenter.BasePresenter;

import dagger.Component;

/**
 * Created by cchao on 2016/8/23.
 * E-mail:   cchao1024@163.com
 * Description: application
 */
@Component ( modules = { AppModule.class , ApiModule.class } )
public interface AppComponent {
        void inject ( BasePresenter presenter );
}
