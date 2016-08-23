package com.github.cchao.touchnews.di.module;

import android.content.Context;

import dagger.Module;
import dagger.Provides;

/**
 * Created by cchao on 2016/8/23.
 * E-mail:   cchao1024@163.com
 * Description:
 */
@Module
public final class AppModule {

        private final Context mContext;

        AppModule ( Context context ) {
                mContext = context;
        }

        @Provides
        Context provideContext ( ) {
                return mContext;
        }
}