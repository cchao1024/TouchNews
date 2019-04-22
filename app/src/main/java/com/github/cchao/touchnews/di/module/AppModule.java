package com.github.cchao.touchnews.di.module;

import android.app.Application;

import dagger.Module;
import dagger.Provides;

/**
 * Created by cchao on 2016/8/23.
 * E-mail:   cchao1024@163.com
 * Description:
 */
@Module
public final class AppModule {

    private Application application;

    public AppModule(Application application) {
        this.application = application;
    }

    @Provides
    public Application provideApplication() {
        return application;
    }
}