package com.github.cchao.touchnews.ui.activity;

import android.view.MenuItem;
import android.widget.TextView;

import com.github.cchao.touchnews.R;
import com.github.cchao.touchnews.javaBean.Weather;
import com.google.gson.Gson;

import butterknife.Bind;

/**
 * Created by cchao on 2016/5/6.
 * E-mail:   cchao1024@163.com
 * Description: 天气的详情页
 */
public class WeatherDetailActivity extends BaseActivity {
        @Bind ( R.id.tv_weather_position )
        TextView mWeatherPosition;
        @Bind ( R.id.tv_weather_tex )
        TextView mWeatherText;
        @Bind ( R.id.tv_weather_all )
        TextView mWeatherAll;
        Weather mWeatherData;

        @Override
        protected int getLayoutID ( ) {
                return R.layout.activity_weather;
        }

        @Override
        protected void initialize ( ) {
                super.initialize ( );
                //解析传过来的Json
                mWeatherData = new Gson ( ).fromJson ( getIntent ( ).getStringExtra ( "data" ), Weather.class );
                initViews ( );
        }

        /**
         * 赋值
         */
        private void initViews ( ) {
                mWeatherPosition.append ( mWeatherData.getBasic ( ).getCity ( ) + "、" + mWeatherData.getBasic ( ).getCity ( ) );
                mWeatherText.append ( mWeatherData.getNow ( ).getCond ( ).getTxt ( ) );
                //好烦呀，全部打印出来算了
                mWeatherAll.append ( getIntent ( ).getStringExtra ( "data" ) );
        }

        @Override
        public boolean onOptionsItemSelected ( MenuItem item ) {
                if ( item.getItemId ( ) == android.R.id.home ) {
                        finish ( );
                        return true;
                }
                return super.onOptionsItemSelected ( item );
        }
}
