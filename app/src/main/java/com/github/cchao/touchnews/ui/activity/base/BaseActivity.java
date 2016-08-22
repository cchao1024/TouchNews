package com.github.cchao.touchnews.ui.activity.base;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.github.cchao.touchnews.R;
import com.umeng.analytics.MobclickAgent;

import butterknife.ButterKnife;

/**
 * Created by H on 2016/3/22.
 * E-mail:  cchao1024@163.com
 * Description:
 */
public abstract class BaseActivity extends AppCompatActivity {
        protected Toolbar mToolbar;
        public Context mContext = this;

        protected abstract
        @LayoutRes
        int getLayoutID ( );

        @Override
        protected void onCreate ( Bundle savedInstanceState ) {
                super.onCreate ( savedInstanceState );
                setContentView ( getLayoutID ( ) );
                ButterKnife.bind ( this );
                setToolbar ( );
                initialize ( );
        }
        private void setToolbar ( ) {
                mToolbar = ButterKnife.findById ( this, R.id.toolbar );
                if ( null != mToolbar ) {
                        setSupportActionBar ( mToolbar );
                        getSupportActionBar ( ).setHomeButtonEnabled ( true );
                        getSupportActionBar ( ).setDisplayHomeAsUpEnabled ( true );
                }
        }
        protected void initialize ( ) {}

        @Override
        protected void onPause ( ) {
                super.onPause ( );
                MobclickAgent.onPause ( this );
        }

        @Override
        protected void onResume ( ) {
                super.onResume ( );
                MobclickAgent.onResume ( this );
        }

        @Override
        protected void onDestroy ( ) {
                super.onDestroy ( );
                ButterKnife.unbind ( this );
        }
}
