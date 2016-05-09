package com.github.armstrong.touchnews.ui.fragment.base;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.github.armstrong.touchnews.R;
import com.github.armstrong.touchnews.ui.activity.HomeActivity;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by cchao on 2016/5/9.
 * E-mail:   cchao1024@163.com
 * Description:
 */
public class BaseFragment extends BaseLazyFragment{
        @Bind ( R.id.toolbar )
        protected Toolbar mToolbar;

        @Override
        public void onViewCreated ( View view,  Bundle savedInstanceState ) {
                super.onViewCreated ( view, savedInstanceState );
                ButterKnife.bind ( this, view );
        }
        @Override
        public void onFirstUserVisible ( ) {
                super.onFirstUserVisible ( );
                setSupportActionBar ();

        }
        @Override
        public void onUserVisible ( ) {
                super.onUserVisible ( );
                setSupportActionBar ( );
        }
        private void setSupportActionBar ( ) {
                ( ( AppCompatActivity ) getActivity ( ) ).setSupportActionBar ( mToolbar );
                ( ( HomeActivity ) getActivity ( ) ).addDrawerListener ( mToolbar );
        }
}
