package com.github.cchao.touchnews;

import android.app.Application;
import android.content.Context;
import android.content.res.Configuration;

import com.apkfuns.logutils.LogLevel;
import com.apkfuns.logutils.LogUtils;
import com.umeng.analytics.MobclickAgent;

/**
 * * Created by H on 2016/3/12.
 */
public class BaseApplication extends Application {
        private static Context context;

        @Override
        public void onCreate ( ) {
                super.onCreate ( );
                context = getApplicationContext ( );
                LogUtils.getLogConfig ( )
                        .configAllowLog ( true )
                        .configTagPrefix ( "LogUtils" )
                        .configShowBorders ( true )
                        .configLevel ( LogLevel.TYPE_VERBOSE );

                MobclickAgent.startWithConfigure ( new MobclickAgent.UMAnalyticsConfig ( this, "5739942de0f55a0b3c001aab", "APP0" ) );
        }

        @Override
        public void onLowMemory ( ) {
                super.onLowMemory ( );
        }

        @Override
        public void onConfigurationChanged ( Configuration newConfig ) {
                super.onConfigurationChanged ( newConfig );
        }

        public static Context getContext ( ) {
                return context;
        }
}
