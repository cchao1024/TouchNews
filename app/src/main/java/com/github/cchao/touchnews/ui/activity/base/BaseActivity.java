package com.github.cchao.touchnews.ui.activity.base;

import android.content.Context;
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
public  class BaseActivity extends AppCompatActivity {
        protected Toolbar mToolbar=null;
        public Context mContext=this;
        @Override
        public void setContentView ( int layoutResID ) {
                super.setContentView(layoutResID);
                mToolbar = ButterKnife. findById (this,R.id.toolbar );
                if (null != mToolbar) {
                        setSupportActionBar(mToolbar);
                        getSupportActionBar().setHomeButtonEnabled(true);
                        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
                }
        }

        @Override
        protected void onPause ( ) {
                super.onPause ( );
                MobclickAgent.onPause ( this);
        }

        @Override
        protected void onResume ( ) {
                super.onResume ( );
                MobclickAgent.onResume ( this);
        }
}
