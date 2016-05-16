package com.github.cchao.touchnews.ui.fragment;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.github.cchao.touchnews.R;
import com.github.cchao.touchnews.adapter.JokeFragmentsPagerAdapter;
import com.github.cchao.touchnews.presenter.JokeFragmentsContainerPresenter;
import com.github.cchao.touchnews.presenter.i.IFragmentsContainerPresenter;
import com.github.cchao.touchnews.ui.fragment.base.BaseFragment;
import com.github.cchao.touchnews.view.FragmentsContainerView;

import java.util.List;

import butterknife.Bind;

/**
 * Created by cchao on 2016/4/25.
 * E-mail:   cchao1024@163.com
 * Description: 开心一下容器Fragment
 */
public class JokeContainerFragment extends BaseFragment implements FragmentsContainerView {
        @Bind ( R.id.toolbar )
        Toolbar mToolbar;
        @Bind ( R.id.tab_joke_image )
        TabLayout mTabLayout;
        @Bind ( R.id.viewpager_joke_image )
        ViewPager mViewPager;
        FragmentPagerAdapter mFragmentsPagerAdapter;
        IFragmentsContainerPresenter mPresenter;
        @Override
        public void onCreate ( Bundle savedInstanceState ) {
                super.onCreate ( savedInstanceState );
                setHasOptionsMenu ( true );
        }
        @Override
        public View onCreateView ( LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState ) {
                super.onCreateView ( inflater, container, savedInstanceState );
                View view = inflater.inflate ( R.layout.fragment_joke_container, null );
                return view;
        }


        @Override
        public void onFirstUserVisible ( ) {
                super.onFirstUserVisible ( );
                mPresenter = new JokeFragmentsContainerPresenter ( this );
                mPresenter.setFragments ( );
                mToolbar.setTitle ( R.string.joke );
        }

        @Override
        public void onCreateOptionsMenu ( Menu menu, MenuInflater inflater ) {
                inflater.inflate ( R.menu.menu_main, menu );
                super.onCreateOptionsMenu ( menu, inflater );
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
        public void onSetFragment ( List fragments, String[] titles ) {
                mFragmentsPagerAdapter = new JokeFragmentsPagerAdapter ( getActivity ( ).getSupportFragmentManager ( ), titles, fragments );
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
