package com.github.cchao.touchnews.ui.fragment;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.github.cchao.touchnews.R;
import com.github.cchao.touchnews.contract.FragmentContainerContract;
import com.github.cchao.touchnews.presenter.NewsFragmentsContainerPresenter;
import com.github.cchao.touchnews.ui.adapter.NewsFragmentsPagerAdapter;
import com.github.cchao.touchnews.ui.fragment.base.BaseFragment;

import java.util.List;

import butterknife.Bind;

/**
 * Created by cchao on 2016/3/30.
 * E-mail:   cchao1024@163.com
 * Description: 新闻资讯容器Fragment
 */
public class NewsContainerFragment extends BaseFragment implements FragmentContainerContract.View {
        @Bind ( R.id.tab_news )
        TabLayout mTabLayout;
        @Bind ( R.id.viewpager_news )
        ViewPager mViewPager;
        FragmentPagerAdapter mFragmentsPagerAdapter;
        FragmentContainerContract.Presenter mPresenter;

        @Override
        public void onCreate ( Bundle savedInstanceState ) {
                super.onCreate ( savedInstanceState );
                setHasOptionsMenu ( true );
        }

        protected int getLayoutId ( ) {
                return R.layout.fragment_news_container;
        }

        @Override
        public void bindPresenter ( ) {
                mPresenter = new NewsFragmentsContainerPresenter ( this );
        }
        @Override
        public void onFirstUserVisible ( ) {
                super.onFirstUserVisible ( );
                mToolbar.setTitle ( R.string.news );
                mPresenter.onStart ( );
        }

        @Override
        public boolean onOptionsItemSelected ( MenuItem item ) {
                switch ( item.getItemId ( ) ) {
                        case R.id.action_search:
                                return true;
                }
                return false;
        }

        @Override
        public void onCreateOptionsMenu ( Menu menu, MenuInflater inflater ) {
                inflater.inflate ( R.menu.menu_main, menu );
                super.onCreateOptionsMenu ( menu, inflater );
        }

        /**
         * 设置新闻频道块
         *
         * @param fragments 块
         * @param titles    标题
         */
        @Override
        public void setFragment ( List< NewsListFragments > fragments, String[] titles ) {
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
