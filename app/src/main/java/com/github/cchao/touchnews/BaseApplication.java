package com.github.cchao.touchnews;

import android.app.Application;
import android.content.Context;
import android.content.res.Configuration;

import com.apkfuns.logutils.LogLevel;
import com.apkfuns.logutils.LogUtils;
import com.github.cchao.touchnews.di.component.AppComponent;
import com.github.cchao.touchnews.di.component.DaggerAppComponent;
import com.github.cchao.touchnews.di.module.ApiModule;
import com.github.cchao.touchnews.di.module.AppModule;
import com.umeng.analytics.MobclickAgent;

/**
 * * Created by H on 2016/3/12.
 */
public class BaseApplication extends Application {
        private static Context context;
        private static AppComponent mAppComponent;

        public static AppComponent getAppComponent ( ) {
                return mAppComponent;
        }

        public static Context getContext ( ) {
                return context;
        }

        @Override
        public void onCreate ( ) {
                super.onCreate ( );
                context = getApplicationContext ( );
                setupLogUtils ( );
                setupComponent ( );
                MobclickAgent.startWithConfigure ( new MobclickAgent.UMAnalyticsConfig ( this, "5739942de0f55a0b3c001aab", "APP0" ) );
        }

        /**
         * 设置LogUtil
         */
        private void setupLogUtils ( ) {
                LogUtils.getLogConfig ( )
                        .configAllowLog ( true )
                        .configTagPrefix ( "TouchNews" )
                        .configShowBorders ( true )
                        .configLevel ( LogLevel.TYPE_VERBOSE );
        }

        /**
         * 设置AppComponent
         */
        private void setupComponent ( ) {
                mAppComponent = DaggerAppComponent.builder ( )
                        .appModule ( new AppModule ( this ) )
                        .apiModule ( new ApiModule ( ) )
                        .build ( );
        }

        @Override
        public void onLowMemory ( ) {
                super.onLowMemory ( );
        }

        @Override
        public void onConfigurationChanged ( Configuration newConfig ) {
                super.onConfigurationChanged ( newConfig );
        }
}
