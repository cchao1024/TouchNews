package com.github.cchao.touchnews.ui.adapter;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v7.app.AppCompatActivity;

import com.github.cchao.touchnews.ui.fragment.base.BaseLazyFragment;

import java.util.List;

/**
 * Created by cchao on 2016/3/30.
 * E-mail:   cchao1024@163.com
 * Description:
 */
public class HomeFragmentPagerAdapter extends FragmentPagerAdapter {
        private List< BaseLazyFragment > mListFragments = null;
        AppCompatActivity mActivity;

        public HomeFragmentPagerAdapter ( FragmentManager fm, List fragments , AppCompatActivity activity) {
                super ( fm );
                mListFragments = fragments;
                mActivity=activity;
        }

        @Override
        public int getCount ( ) {
                if ( mListFragments != null ) {
                        return mListFragments.size ( );
                } else {
                        return 0;
                }
        }

        @Override
        public BaseLazyFragment getItem ( int position ) {
                if ( mListFragments != null && position >= 0 && position < mListFragments.size ( ) ) {
                        return mListFragments.get ( position );
                } else {
                        return null;
                }
        }

}
