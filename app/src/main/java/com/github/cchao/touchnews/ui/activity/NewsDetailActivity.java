package com.github.cchao.touchnews.ui.activity;

import android.content.Intent;
import android.graphics.Bitmap;
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

import com.github.cchao.touchnews.R;
import com.github.cchao.touchnews.javaBean.news.Contentlist;
import com.github.cchao.touchnews.util.ImageUtil;

import butterknife.Bind;

/**
 * Created by cchao on 2016/4/7.
 * E-mail:   cchao1024@163.com
 * Description: 新闻详情页 - 使用WebView 加载外链
 */
public class NewsDetailActivity extends BaseActivity implements Toolbar.OnMenuItemClickListener {
        @Bind ( R.id.iv_new_detail_top )
        ImageView mImageViewTop;
        @Bind ( R.id.pb_new_detail )
        ProgressBar mProgressBar;
        @Bind ( R.id.webview_new_detail )
        WebView mWebView;
        Contentlist mContentlist;

        @Override
        protected int getLayoutID ( ) {
                return R.layout.activity_new_detail ;
        }

        @Override
        protected void initialize ( ) {
                super.initialize ( );
                initViews ( );
                setWebView ( );
                setNavigationClick ( );
        }

        private void setNavigationClick ( ) {
                mToolbar.setNavigationOnClickListener ( new View.OnClickListener ( ) {
                        @Override
                        public void onClick ( View v ) {
                                onBackPressed ( );
                        }
                } );
        }

        @Override
        public void onBackPressed ( ) {
                mWebView.setVisibility ( View.INVISIBLE );
                super.onBackPressed ( );
        }

        private void initViews ( ) {
                mContentlist = ( Contentlist ) this.getIntent ( ).getSerializableExtra ( "contentList" );
                ImageUtil.displayImage ( this, mContentlist.getImageurls ( ).get ( 0 ).getUrl ( ), mImageViewTop );
                mWebView.loadUrl ( mContentlist.getLink ( ) );
                if ( mToolbar != null ) {
                        mToolbar.setTitle ( mContentlist.getSource ( ) );
                        mToolbar.setOnMenuItemClickListener ( this );
                        setSupportActionBar ( mToolbar );
                }
        }

        @Override
        protected void onPause ( ) {
                super.onPause ( );
                mWebView.onPause ();
        }

        private void setWebView ( ) {
                WebSettings settings = mWebView.getSettings ( );
                mWebView.setWebViewClient ( new MyWebViewClient ( mProgressBar ) );
                mWebView.setWebChromeClient ( new MyWebChromeClient ( mProgressBar ) );
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

        @Override
        public boolean onCreateOptionsMenu ( Menu menu ) {
                // Inflate the menu; this adds items to the action bar if it is present.
                getMenuInflater ( ).inflate ( R.menu.menu_news_detail, menu );
                return true;
        }

        @Override
        public boolean onMenuItemClick ( MenuItem item ) {
                switch ( item.getItemId ( ) ) {
                        //分享
                        case R.id.action_share:
                                if ( mContentlist != null ) {
                                        Intent intent = new Intent ( Intent.ACTION_SEND );
                                        intent.setType ( "text/plain" );
                                        intent.putExtra ( Intent.EXTRA_SUBJECT, "分享" );
                                        intent.putExtra ( Intent.EXTRA_TEXT, mContentlist.getLink () );
                                        intent.putExtra ( Intent.EXTRA_TITLE,mContentlist.getTitle ());
                                        intent.setFlags ( Intent.FLAG_ACTIVITY_NEW_TASK );
                                        startActivity ( Intent.createChooser ( intent, "请选择" ) );
                                }
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
