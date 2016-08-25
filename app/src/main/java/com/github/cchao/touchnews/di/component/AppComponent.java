package com.github.cchao.touchnews.di.component;

import com.github.cchao.touchnews.di.module.ApiModule;
import com.github.cchao.touchnews.di.module.AppModule;
import com.github.cchao.touchnews.util.BaiDuApiService;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by cchao on 2016/8/23.
 * E-mail:   cchao1024@163.com
 * Description: application
 */
@Singleton
@Component ( modules = { AppModule.class , ApiModule.class } )
public interface AppComponent {
        BaiDuApiService getBaiDuApiService ( );
}
