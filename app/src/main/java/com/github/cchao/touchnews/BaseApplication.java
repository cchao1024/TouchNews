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
import com.github.cchao.touchnews.util.BaiDuApiService;
import com.umeng.analytics.MobclickAgent;

/**
 * * Created by H on 2016/3/12.
 */
public class BaseApplication extends Application {
    public static BaiDuApiService mBaiDuApiService;
    public static AppComponent mAppComponent;
    private static Application mApplication;

    public static AppComponent getAppComponent() {
        mBaiDuApiService = mAppComponent.getBaiDuApiService();
        return mAppComponent;
    }

    public static Context getContext() {
        return mApplication;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        setupLogUtils();
        setupComponent();
        mApplication = this;
        MobclickAgent.startWithConfigure(new MobclickAgent.UMAnalyticsConfig(this, "5739942de0f55a0b3c001aab", "APP0"));
    }

    /**
     * 设置LogUtil
     */
    private void setupLogUtils() {
        LogUtils.getLogConfig()
            .configAllowLog(true)
            .configTagPrefix("TouchNews")
            .configShowBorders(true)
            .configLevel(LogLevel.TYPE_VERBOSE);
    }

    /**
     * 设置AppComponent
     */
    private void setupComponent() {
        mAppComponent = DaggerAppComponent.builder()
            .appModule(new AppModule(this))
            .apiModule(new ApiModule())
            .build();
        mBaiDuApiService = mAppComponent.getBaiDuApiService();

    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
    }
}
