package com.github.cchao.touchnews.ui.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.widget.TextView;

import com.github.cchao.touchnews.R;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by cchao on 2016/5/16.
 * E-mail:   cchao1024@163.com
 * Description: 欢迎页
 */
public class SplashActivity extends Activity {
    final int DELAY_TIME = 500;
    @Bind(R.id.tv_version)
    TextView mVersionText;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        ButterKnife.bind(this);
        setVersionText();
        setStartAty();
    }

    /**
     * 底部显示版本号
     */
    private void setVersionText() {
        mVersionText.append(getAppVersionName(this));
    }

    //获取当前版本号
    private String getAppVersionName(Context context) {
        String versionName = "";
        try {
            PackageManager packageManager = context.getPackageManager();
            PackageInfo packageInfo = packageManager.getPackageInfo(getPackageName(), 0);
            versionName = packageInfo.versionName;
            if (TextUtils.isEmpty(versionName)) {
                return "";
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return versionName;
    }

    /**
     * DELAY_TIME 延迟后启动HomeAty
     */
    private void setStartAty() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(SplashActivity.this, HomeActivity.class);
                startActivity(intent);
                finish();
            }
        }, DELAY_TIME);
    }


}
