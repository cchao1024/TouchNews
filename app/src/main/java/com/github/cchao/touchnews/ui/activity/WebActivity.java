package com.github.cchao.touchnews.ui.activity;

import android.webkit.WebSettings;
import android.webkit.WebView;

import com.github.cchao.touchnews.R;
import com.github.cchao.touchnews.util.Keys;
import com.github.cchao.touchnews.util.LogUtil;

import butterknife.Bind;

/**
 * Created by cchao on 2016/8/19.
 * E-mail:   cchao1024@163.com
 * Description:  网页显示页
 */
public class WebActivity extends BaseActivity {

        @Bind ( R.id.webview_article_detail )
        WebView mWebView;

        @Override
        protected int getLayoutID ( ) {
                return R.layout.activity_web;
        }

        @Override
        protected void initialize ( ) {
                super.initialize ( );
                setWebView ( );
                mWebView.loadUrl ( getIntent ( ).getStringExtra ( Keys.URL ) );
                LogUtil.i ( getIntent ( ).getStringExtra ( Keys.URL ) );
        }

        private void setWebView ( ) {
                WebSettings settings = mWebView.getSettings ( );
                settings.setSupportZoom ( true );          //支持缩放
                settings.setBlockNetworkImage ( true );  //设置图片最后加载
//                settings.setBlockNetworkLoads ( true );
//                settings.setDomStorageEnabled ( true );
                settings.setDatabaseEnabled ( true );
//                String cacheDirPath = mContext.getFilesDir ( ).getAbsolutePath()+ CacheUtil.WEB_CACAH_DIRNAME;
                //缓存
//                settings.setAppCachePath (  );
                settings.setCacheMode ( WebSettings.LOAD_CACHE_ELSE_NETWORK );
                settings.setAppCacheEnabled ( true );
//                settings.setAppCachePath ( cacheDirPath );
//                settings.setBuiltInZoomControls ( true );  //启用内置缩放装置
                settings.setJavaScriptEnabled ( true );    //启用JS脚本
               /* mWebView.setOnKeyListener ( new View.OnKeyListener() {
                        @Override
                        public boolean onKey(View v, int keyCode, KeyEvent event) {
                                if (event.getAction() == KeyEvent.ACTION_DOWN) {
                                        if (keyCode == KeyEvent.KEYCODE_BACK && mWebView.canGoBack()) {
                                                mWebView.goBack();   //后退
                                                return true;
                                        }
                                }
                                return false;
                        }
                });*/

        }
}
