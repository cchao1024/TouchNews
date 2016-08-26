package com.github.cchao.touchnews.ui.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.github.cchao.touchnews.ui.fragment.NewsListFragments;

import java.util.List;

/**
 * Created by cchao on 2016/3/30.
 * E-mail:   cchao1024@163.com
 * Description: 新闻频道 页面Adapter
 */
public class NewsFragmentsPagerAdapter extends FragmentPagerAdapter {
        private List< NewsListFragments > mListFragments = null;
        private String[] mTitles = null;

        public NewsFragmentsPagerAdapter ( FragmentManager fm, String[] titles, List< NewsListFragments > fragments ) {
                super ( fm );
                mListFragments = fragments;
                mTitles = titles;

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
        public Fragment getItem ( int position ) {
                if ( mListFragments != null && position >= 0 && position < mListFragments.size ( ) ) {
                        return mListFragments.get ( position );
                } else {
                        return null;
                }
        }

        @Override
        public CharSequence getPageTitle ( int position ) {
                return mTitles[ position ];
        }
}
