package com.github.armstrong.touchnews.ui.fragment;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.github.armstrong.touchnews.R;
import com.github.armstrong.touchnews.adapter.NewsFragmentsPagerAdapter;
import com.github.armstrong.touchnews.presenter.i.IFragmentsContainerPresenter;
import com.github.armstrong.touchnews.presenter.NewsFragmentsPresenter;
import com.github.armstrong.touchnews.ui.fragment.base.BaseLazyFragment;
import com.github.armstrong.touchnews.view.FragmentsContainerView;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by cchao on 2016/3/30.
 * E-mail:   cchao1024@163.com
 * Description:
 */
public class NewsContainerFragment extends BaseLazyFragment implements FragmentsContainerView{
        @Bind ( R.id.tab_news )
        TabLayout mTabLayout;
        @Bind ( R.id.viewpager_news )
        ViewPager mViewPager;
        FragmentPagerAdapter mFragmentsPagerAdapter;
        IFragmentsContainerPresenter mPresenter;

        @Override
        public void onCreate ( Bundle savedInstanceState ) {
                super.onCreate ( savedInstanceState );

        }
        private void initiation ( ) {
                mPresenter=new NewsFragmentsPresenter ( this);
                mPresenter.setFragments ( );
        }
        @Override
        public View onCreateView ( LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState ) {
                super.onCreateView ( inflater, container, savedInstanceState );
                View view = inflater.inflate ( R.layout.fragment_news, null );
                ButterKnife.bind ( this, view );
                return view;
        }

        @Override
        public void onViewCreated ( View view, Bundle savedInstanceState ) {
                super.onViewCreated ( view, savedInstanceState );
                initiation ( );
        }
        @Override
        public void initFragment ( List fragments, String[] titles ) {
                mFragmentsPagerAdapter = new NewsFragmentsPagerAdapter ( getActivity ( ).getSupportFragmentManager ( ), titles, fragments );
                mViewPager.setOffscreenPageLimit ( fragments.size ( ) );
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
                mTabLayout.setupWithViewPager ( mViewPager );

        }
}
