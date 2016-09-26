package com.github.cchao.touchnews.ui.fragment.base;

import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.github.cchao.touchnews.R;
import com.github.cchao.touchnews.ui.activity.HomeActivity;

import butterknife.ButterKnife;

/**
 * Created by cchao on 2016/5/9.
 * E-mail:   cchao1024@163.com
 * Description:
 */
public abstract class BaseFragment extends BaseLazyFragment {
        protected Toolbar mToolbar;
        protected View mRootView;

        @Nullable
        @Override
        public View onCreateView ( LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState ) {
                mRootView = inflater.inflate ( getLayoutId ( ), null );
                ButterKnife.bind ( this, mRootView );
                mToolbar = ButterKnife.findById ( mRootView, R.id.toolbar );
                return mRootView;
        }

        @Override
        public void onViewCreated ( View view, Bundle savedInstanceState ) {
                super.onViewCreated ( view, savedInstanceState );
                initialize ( );
        }

        protected void initialize ( ) {}

        @Override
        public void onFirstUserVisible ( ) {
                super.onFirstUserVisible ( );
                setSupportActionBar ();
        }

        //布局ID
        protected abstract
        @LayoutRes
        int getLayoutId ( );

        @Override
        public void onUserVisible ( ) {
                super.onUserVisible ( );
                setSupportActionBar ( );
        }

        /**
         * 设置toolbar
         */
        private void setSupportActionBar ( ) {
                ( ( AppCompatActivity ) getActivity ( ) ).setSupportActionBar ( mToolbar );
                ( ( HomeActivity ) getActivity ( ) ).addDrawerListener ( mToolbar );
        }
}
