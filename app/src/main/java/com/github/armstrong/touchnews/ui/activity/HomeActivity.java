package com.github.armstrong.touchnews.ui.activity;

import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.github.armstrong.touchnews.R;
import com.github.armstrong.touchnews.adapter.HomeFragmentPagerAdapter;
import com.github.armstrong.touchnews.javaBean.Weather.Weather;
import com.github.armstrong.touchnews.presenter.i.IHomePresenter;
import com.github.armstrong.touchnews.presenter.HomePresenter;
import com.github.armstrong.touchnews.ui.activity.base.BaseActivity;
import com.github.armstrong.touchnews.view.HomeView;
import com.github.armstrong.touchnews.widget.MyViewpager;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

public class HomeActivity extends BaseActivity implements HomeView {
        @Bind ( R.id.drawer_home )
        DrawerLayout mDrawerLayout;
        @Bind ( R.id.viewpager_home_fragments_container )
        MyViewpager mFragmentViewPager;
        @Bind ( R.id.navigation_home )
        NavigationView mNavigationView;
        IHomePresenter mHomePresenter;
        ActionBarDrawerToggle mActionBarDrawerToggle;


        @Override
        protected void onCreate ( Bundle savedInstanceState ) {
                super.onCreate ( savedInstanceState );
                setContentView ( R.layout.activity_home );
                ButterKnife.bind ( this );
                initNavigation ( );
                initViews ( );
        }

        private void initNavigation ( ) {
                mActionBarDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout, mToolbar, R.string.open,
                        R.string.close);
                mActionBarDrawerToggle.syncState();
                mDrawerLayout.addDrawerListener (mActionBarDrawerToggle);
                mNavigationView.setNavigationItemSelectedListener ( new NavigationView.OnNavigationItemSelectedListener ( ) {
                        @Override
                        public boolean onNavigationItemSelected ( MenuItem item ) {
                                Menu menu = mNavigationView.getMenu ( );
                                for ( int i = 0 ; i < menu.size ( ) ; i++ ) {
                                        if ( item.getOrder ( ) == i + 1 ) {
                                                mFragmentViewPager.setCurrentItem ( i, false );
                                                item.setChecked ( true );
                                        } else {
                                                item.setChecked ( false );
                                        }
                                }
                                mDrawerLayout.closeDrawer ( GravityCompat.START );
                                return true;
                        }
                } );
        }

        private void initViews ( ) {
                mHomePresenter = new HomePresenter ( this );
                mHomePresenter.getFragments ( );
                mHomePresenter.getNavigation ( );
//                FloatingActionButton fab = ( FloatingActionButton ) findViewById ( R.id.fab );
//                fab.setOnClickListener ( new View.OnClickListener ( ) {
//                        @Override
//                        public void onClick ( View view ) {
//                                Snackbar.make ( view, "Replace with your own action", Snackbar.LENGTH_LONG )
//                                        .setAction ( "Action", null ).show ( );
//                        }
//                } );
        }

        @Override
        public boolean onCreateOptionsMenu ( Menu menu ) {
                // Inflate the menu; this adds items to the action bar if it is present.
                getMenuInflater ( ).inflate ( R.menu.menu_main, menu );
                return true;
        }

        @Override
        public boolean onOptionsItemSelected ( MenuItem item ) {
                // Handle action bar item clicks here. The action bar will
                // automatically handle clicks on the Home/Up button, so long
                // as you specify a parent activity in AndroidManifest.xml.
                int id = item.getItemId ( );

                //noinspection SimplifiableIfStatement
                if ( id == R.id.action_settings ) {
                        return true;
                }

                return super.onOptionsItemSelected ( item );
        }

        @Override
        public void setFragmentPager ( List fragments ) {
                mFragmentViewPager.setOffscreenPageLimit ( fragments.size ( ) );
                mFragmentViewPager.setAdapter ( new HomeFragmentPagerAdapter ( getSupportFragmentManager ( ), fragments ) );
        }

        @Override
        public void setNavigation ( Weather weather ) {
//                Log.e("setNavigation", weather.getBasic().getCity());
                View headerView = mNavigationView.getHeaderView ( 0 );
//                ImageView iconWeather = ( ( ImageView ) headerView.findViewById ( R.id.iv_weather_icon ) );
                //天气类型 - 晴、多云
                TextView tvTypeText = ( ( TextView ) headerView.findViewById ( R.id.tv_weather_txt ) );
                //当前气温 - 32 *C
                TextView tvTemperature = ( ( TextView ) headerView.findViewById ( R.id.tv_weather_temperature ) );
                //城市位置 - 广州
                TextView tvPosition = ( ( TextView ) headerView.findViewById ( R.id.tv_weather_position ) );
                //天气类型代码
                int weatherCode = Integer.valueOf ( weather.getNow ( ).getCond ( ).getCode ( ) );
                int[] weatherCodeArr = getResources ( ).getIntArray ( R.array.weather_code );
                int[] weatherDrawableID = getResources ( ).getIntArray ( R.array.weather_icon );
                //设置天气类型图标
//                iconWeather.setImageResource ( R.drawable.weather_999 );
                for ( int i = 0 ; i < weatherCodeArr.length ; i++ ) {
                        if ( weatherCodeArr[ i ] == weatherCode ) {
                                tvTypeText.setCompoundDrawablesWithIntrinsicBounds (0,weatherDrawableID[ i ],0,0 );
                        }
                }
                tvTypeText.setText ( weather.getNow ( ).getCond ( ).getTxt ( ) );
                tvTemperature.setText ( weather.getNow ( ).getTmp ( ) );
                tvPosition.setText ( weather.getBasic ( ).getCity ( ) );

        }
}
