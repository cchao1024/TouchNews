package com.github.cchao.touchnews.ui.activity;

import android.content.Intent;
import android.content.res.TypedArray;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.github.cchao.touchnews.R;
import com.github.cchao.touchnews.contract.HomeContract;
import com.github.cchao.touchnews.javaBean.Weather;
import com.github.cchao.touchnews.presenter.HomePresenter;
import com.github.cchao.touchnews.ui.adapter.HomeFragmentPagerAdapter;
import com.github.cchao.touchnews.util.SnackBarUtil;
import com.github.cchao.touchnews.widget.MyViewpager;
import com.google.gson.Gson;

import java.util.List;

import butterknife.Bind;

/**
 * 主界面
 */
public class HomeActivity extends BaseActivity implements HomeContract.View {
    private static final int OUT_TIME = 2000;
    @Bind(R.id.drawer_home)
    DrawerLayout mDrawerLayout;
    @Bind(R.id.viewpager_home_fragments_container)
    MyViewpager mFragmentViewPager;
    @Bind(R.id.navigation_home)
    NavigationView mNavigationView;
    HomeContract.Presenter mHomePresenter;
    ActionBarDrawerToggle mActionBarDrawerToggle;
    /**
     * 再按一次退出程序
     */
    long preTime = 0;

    @Override
    protected int getLayoutID() {
        return R.layout.activity_home;
    }

    @Override
    protected void initialize() {
        super.initialize();
        addDrawerListener(mToolbar);
        initNavigation();
        initViews();
    }

    public void addDrawerListener(Toolbar toolbar) {
        mActionBarDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout, toolbar, R.string.open,
            R.string.close);
        mActionBarDrawerToggle.syncState();
        mDrawerLayout.addDrawerListener(mActionBarDrawerToggle);
    }

       /* @Override
        public boolean onCreateOptionsMenu ( Menu menu ) {
                // Inflate the menu; this adds items to the action bar if it is present.
                getMenuInflater ( ).inflate ( R.menu.menu_main, menu );
                return true;
        }*/

    private void initNavigation() {
                /*mActionBarDrawerToggle = new ActionBarDrawerToggle ( this, mDrawerLayout, mToolbar, R.string.open,
                        R.string.close );
                mActionBarDrawerToggle.syncState ( );
                mDrawerLayout.addDrawerListener ( mActionBarDrawerToggle );*/
        mNavigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem item) {
                Menu menu = mNavigationView.getMenu();
                switch (item.getItemId()) {
                    case R.id.menu_item_drawer_news:
                        mFragmentViewPager.setCurrentItem(0, false);
                        break;
                    case R.id.menu_item_drawer_joke:
                        mFragmentViewPager.setCurrentItem(1, false);
                        break;
                    case R.id.menu_item_drawer_music:
                        mFragmentViewPager.setCurrentItem(2, false);
                        break;
                    case R.id.menu_item_drawer_chat:
                        mFragmentViewPager.setCurrentItem(3, false);
                        break;
                    case R.id.nav_theme:
//                                                        mFragmentViewPager.setCurrentItem ( i, false );
                        break;
                    case R.id.nav_night:
//                                                        mFragmentViewPager.setCurrentItem ( i, false );
                        break;
                    case R.id.nav_setting:
//                                                        mFragmentViewPager.setCurrentItem ( i, false );
                        break;
                    case R.id.nav_suggestion:
//                                                        mFragmentViewPager.setCurrentItem ( i, false );
                        break;
                    case R.id.nav_about:
//                                                        mFragmentViewPager.setCurrentItem ( i, false );
                        break;
                }
                mDrawerLayout.closeDrawer(GravityCompat.START);
                return true;
            }
        });
    }

    private void initViews() {
        mHomePresenter = new HomePresenter(this);
        mHomePresenter.getFragments();
        mHomePresenter.getNavigation();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void setFragmentPager(List fragments) {
        mFragmentViewPager.setOffscreenPageLimit(fragments.size());
        mFragmentViewPager.setAdapter(new HomeFragmentPagerAdapter(getSupportFragmentManager(), fragments, this));
    }

    @Override
    public void setNavigation(final Weather weather) {
//                Log.e("setNavigation", weather.getBasic().getCity());
        if (weather != null && weather.getNow() != null) {
            View headerView = mNavigationView.getHeaderView(0);
//                ImageView iconWeather = ( ( ImageView ) headerView.findViewById ( R.id.iv_weather_icon ) );
            //天气类型 - 晴、多云
            TextView tvTypeText = ((TextView) headerView.findViewById(R.id.tv_weather_txt));
            //当前气温 - 32 *C
            TextView tvTemperature = ((TextView) headerView.findViewById(R.id.tv_weather_temperature));
            //城市位置 - 广州
            TextView tvPosition = ((TextView) headerView.findViewById(R.id.tv_weather_position));
            //天气类型代码
            int weatherCode = Integer.valueOf(weather.getNow().getCond().getCode());
            int[] weatherCodeArr = getResources().getIntArray(R.array.weather_code);
            tvTypeText.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.weather_999, 0, 0);
            TypedArray ar = getResources().obtainTypedArray(R.array.weather_icon);
            int len = ar.length();
            int[] weatherDrawableID = new int[len];
            for (int i = 0; i < len; i++) {
                weatherDrawableID[i] = ar.getResourceId(i, 0);
            }
            ar.recycle();
            //设置天气类型图标
            for (int i = 0; i < weatherCodeArr.length; i++) {
                if (weatherCodeArr[i] == weatherCode) {
                    tvTypeText.setCompoundDrawablesWithIntrinsicBounds(0, weatherDrawableID[i], 0, 0);
                }
            }
            tvTypeText.setText(weather.getNow().getCond().getTxt());
            tvTemperature.setText(weather.getNow().getTmp());
            tvPosition.setText(weather.getBasic().getCity());
            headerView.findViewById(R.id.layout_drawer_header_weather).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(HomeActivity.this, WeatherDetailActivity.class);
                    intent.putExtra("data", new Gson().toJson(weather));
                    startActivity(intent);
                }
            });
        }
    }

    @Override
    public void onBackPressed() {
        if (System.currentTimeMillis() - preTime <= OUT_TIME) {
            super.onBackPressed();
            finish();
        } else {
            preTime = System.currentTimeMillis();
            SnackBarUtil.showShort(mFragmentViewPager, R.string.again_press_exit);
        }
    }

}
