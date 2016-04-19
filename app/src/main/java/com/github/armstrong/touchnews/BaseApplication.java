package com.github.armstrong.touchnews;

import android.app.Application;
import android.content.Context;
import android.content.res.Configuration;

import com.apkfuns.logutils.LogLevel;
import com.apkfuns.logutils.LogUtils;

/**
 * * Created by H on 2016/3/12.
 */
public class BaseApplication extends Application {
        private static Context context;
        @Override
        public void onCreate ( ) {
                super.onCreate ( );
                context=getApplicationContext ();
                LogUtils.getLogConfig()
                        .configAllowLog(true)
                        .configTagPrefix("LogUtils")
                        .configShowBorders(true)
                        .configLevel( LogLevel.TYPE_VERBOSE);
        }

        @Override
        public void onLowMemory ( ) {
                super.onLowMemory ( );
        }

        @Override
        public void onConfigurationChanged ( Configuration newConfig ) {
                super.onConfigurationChanged ( newConfig );
        }
        public static Context getContext(){
                return context;
        }
}
