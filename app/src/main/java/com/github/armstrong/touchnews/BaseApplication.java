package com.github.armstrong.touchnews;

import android.app.Application;
import android.content.Context;
import android.content.res.Configuration;

/**
 * * Created by H on 2016/3/12.
 */
public class BaseApplication extends Application {
        private static Context context;
        @Override
        public void onCreate ( ) {
                super.onCreate ( );
                context=getApplicationContext ();
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
