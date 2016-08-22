package com.github.cchao.touchnews.ui.fragment.base;

import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.github.cchao.touchnews.presenter.BasePresenter;

import butterknife.ButterKnife;

/**
 * Created by cchao on 2016/8/18.
 * E-mail:   cchao1024@163.com
 * Description:  持有Presenter的Fragment
 */
public abstract class PresentFragment < P extends BasePresenter > extends BaseFragment {
        protected View mRootView;
        protected P mPresenter;

        @Nullable
        @Override
        public View onCreateView ( LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState ) {
                mRootView = inflater.inflate ( getLayoutId ( ), null );
                ButterKnife.bind ( this, mRootView );
                return mRootView;
        }

        @Override
        public void onViewCreated ( View view, Bundle savedInstanceState ) {
                super.onViewCreated ( view, savedInstanceState );
                initialize ( );
        }

        protected void initialize ( ) {
                mPresenter = getPresenter ( );
                mPresenter.bindView ( this );
        }

        //布局ID
        protected abstract
        @LayoutRes
        int getLayoutId ( );

        //获取Presenter
         protected abstract P getPresenter ( );

        @Override
        public void onDestroyView ( ) {
                super.onDestroyView ( );
                ButterKnife.unbind ( this );
                if ( mPresenter != null ) {
                        mPresenter.unbindView ( );
                }
        }
}
