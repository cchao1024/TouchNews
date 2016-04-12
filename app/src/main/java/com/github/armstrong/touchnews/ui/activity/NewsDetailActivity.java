package com.github.armstrong.touchnews.ui.activity;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.ProgressBar;

import com.github.armstrong.touchnews.R;
import com.github.armstrong.touchnews.javaBean.news.Contentlist;
import com.github.armstrong.touchnews.ui.activity.base.BaseActivity;
import com.github.armstrong.touchnews.util.ImageUtil;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by cchao on 2016/4/7.
 * E-mail:   cchao1024@163.com
 * Description: 新闻详情页
 */
public class NewsDetailActivity extends BaseActivity implements Toolbar.OnMenuItemClickListener{
        @Bind ( R.id.iv_new_detail_top )
        ImageView mImageViewTop;
        @Bind ( R.id.pb_new_detail )
        ProgressBar mProgressBar;
        @Bind ( R.id.webview_new_detail )
        WebView mWebView;
        Contentlist mContentlist;

        @Override
        public void onCreate ( Bundle savedInstanceState ) {
                super.onCreate ( savedInstanceState );
                setContentView ( R.layout.activity_new_detail );
                ButterKnife.bind ( this );
                initViews ( );
                setWebView ( );
                setNavigationClick();
        }

        private void setNavigationClick ( ) {
                mToolbar.setNavigationOnClickListener ( new View.OnClickListener ( ) {
                        @Override
                        public void onClick ( View v ) {
                                onBackPressed ( );
                        }
                });
        }

        @Override
        public void onBackPressed ( ) {
                mWebView.setVisibility ( View.INVISIBLE );
                super.onBackPressed ( );
        }

        private void initViews ( ) {
                mContentlist = ( Contentlist ) this.getIntent ( ).getSerializableExtra ( "contentList" );
                ImageUtil.displayImage ( this, mContentlist.getImageurls ( ).get ( 0 ).getUrl ( ), mImageViewTop );
//                ViewCompat.setTransitionName ( mImageViewTop,mContentlist.getSource ( ) );
//                mWebView.loadUrl ( "http://www.sina.com" );
                mWebView.loadUrl ( mContentlist.getLink ( ) );
                if ( mToolbar != null ) {
                        mToolbar.setTitle ( mContentlist.getSource ( ) );
                        mToolbar.setOnMenuItemClickListener ( this );
                }
        }

        private void setWebView ( ) {
                WebSettings settings = mWebView.getSettings ( );
                mWebView.setWebViewClient ( new MyWebViewClient ( mProgressBar ) );
                mWebView.setWebChromeClient ( new MyWebChromeClient ( mProgressBar ) );
                settings.setSupportZoom ( true );          //支持缩放
//                settings.setBlockNetworkImage ( true );  //设置图片最后加载
//                settings.setBlockNetworkLoads ( true );
//                settings.setDomStorageEnabled ( true );
                settings.setDatabaseEnabled ( true );
//                String cacheDirPath = mContext.getFilesDir ( ).getAbsolutePath()+ CacheUtil.WEB_CACAH_DIRNAME;
                //缓存
                settings.setCacheMode ( WebSettings.LOAD_CACHE_ELSE_NETWORK );
                settings.setAppCacheEnabled ( true );
//                settings.setAppCachePath ( cacheDirPath );
//                settings.setBuiltInZoomControls ( true );  //启用内置缩放装置
                settings.setJavaScriptEnabled ( true );    //启用JS脚本
        }

        @Override
        public boolean onCreateOptionsMenu ( Menu menu ) {
                // Inflate the menu; this adds items to the action bar if it is present.
                getMenuInflater ( ).inflate ( R.menu.menu_main, menu );
                return true;
        }

        @Override
        public boolean onMenuItemClick ( MenuItem item ) {
                switch ( item.getItemId () ){
                        case R.id.action_edit:
//                                Snackbar
                                break;
                        case R.id.action_share:
//                                msg += "Click share";
                                break;
                        case R.id.action_settings:
//                                msg += "Click setting";
                                break;
                }
                return true;
        }

        public class MyWebViewClient extends WebViewClient {
                ProgressBar mProgressBar;

                /*private  OnPageFinishedListener mPageFinishedListener;
                interface OnPageFinishedListener{
                        void onPageFinishedCallBack ( );
                }
                public void setOnPageFinishedListene(OnPageFinishedListener onPageFinishedListener){
                        mPageFinishedListener=onPageFinishedListener;
                }*/
                public MyWebViewClient ( ProgressBar progressBar ) {
                        super ( );
                        mProgressBar = progressBar;
                }

                @Override
                public void onPageStarted ( WebView view, String url, Bitmap favicon ) {
                        super.onPageStarted ( view, url, favicon );
                        mProgressBar.setVisibility ( View.VISIBLE );
                }

                @Override
                public void onPageFinished ( WebView webView, String url ) {
                        super.onPageFinished ( webView, url );
                        mProgressBar.setVisibility ( View.INVISIBLE );
                       /* if(mPageFinishedListener!=null){
                                mPageFinishedListener.onPageFinishedCallBack ( );
                        }*/
                }
        }

        public class MyWebChromeClient extends WebChromeClient {
                ProgressBar mWebProgressBar;

                public MyWebChromeClient ( ProgressBar mWebProgressBar ) {
                        this.mWebProgressBar = mWebProgressBar;
                }

                @Override
                public void onProgressChanged ( WebView view, int newProgress ) {
                        mWebProgressBar.setProgress ( newProgress );
                }

                @Override
                public void onReceivedTitle ( WebView view, String title ) {
                        super.onReceivedTitle ( view, title );
                }
        }

}
