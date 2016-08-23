package com.github.cchao.touchnews.ui.fragment;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.github.cchao.touchnews.R;
import com.github.cchao.touchnews.ui.adapter.ImageFragmentsPagerAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by cchao on 2016/3/30.
 * E-mail:   cchao1024@163.com
 * Description:
 */
public class ImageFragment extends Fragment {
        @Bind ( R.id.tab_image )
        TabLayout mTabLayout;
        @Bind ( R.id.viewpager_image )
        ViewPager mViewPager;
        String[] mTitles;
        List mFragments;
        FragmentPagerAdapter mFragmentsPagerAdapter;

        @Override
        public void onCreate ( Bundle savedInstanceState ) {
                super.onCreate ( savedInstanceState );
                initData ( );

        }

        @Override
        public View onCreateView ( LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState ) {
                super.onCreateView ( inflater, container, savedInstanceState );
                View view = inflater.inflate ( R.layout.fragment_image, null );
                ButterKnife.bind ( this, view );
                return view;
        }

        @Override
        public void onViewCreated ( View view, Bundle savedInstanceState ) {
                super.onViewCreated ( view, savedInstanceState );
                setTableLayout ( );
        }

        private void initData ( ) {
                mTitles = getResources ( ).getStringArray ( R.array.news_titles );

                mFragments = new ArrayList<> ( );
                for ( int i = 0 ; i < mTitles.length ; i++ ) {
                        Bundle mBundle = new Bundle ( );
                        mBundle.putInt ( "flag", i );
//                        Fragment fragment = new NewsListFragments ( );
//                        fragment.setArguments ( mBundle );
//                        mFragments.add ( i, fragment );
                }
        }

        private void setTableLayout ( ) {

                mFragmentsPagerAdapter = new ImageFragmentsPagerAdapter ( getActivity ( ).getSupportFragmentManager ( ), mTitles, mFragments );
                mViewPager.setOffscreenPageLimit ( mFragments.size ( ) );
                mViewPager.setAdapter ( mFragmentsPagerAdapter );

                mViewPager.addOnPageChangeListener ( new ViewPager.OnPageChangeListener ( ) {
                        @Override
                        public void onPageScrolled ( int position, float positionOffset, int positionOffsetPixels ) {

                        }

                        @Override
                        public void onPageSelected ( int position ) {

                        }

                        @Override
                        public void onPageScrollStateChanged ( int state ) {

                        }
                } );

                mTabLayout.setTabMode ( TabLayout.MODE_SCROLLABLE );
                // 将TabLayout和ViewPager进行关联，让两者联动起来
                mTabLayout.setupWithViewPager ( mViewPager );

        }
}
