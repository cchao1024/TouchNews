package com.github.armstrong.touchnews.ui.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.TextView;

import com.github.armstrong.touchnews.R;
import com.github.armstrong.touchnews.javaBean.Weather;
import com.github.armstrong.touchnews.ui.activity.base.BaseActivity;
import com.google.gson.Gson;

import butterknife.Bind;
import butterknife.ButterKnife;

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
        protected void onCreate ( @Nullable Bundle savedInstanceState ) {
                super.onCreate ( savedInstanceState );
                setContentView ( R.layout.activity_weather );
                ButterKnife.bind ( this );
//             mWeatherData= ( Weather ) (getIntent ().getBundleExtra ( "bundle" ).getSerializable ( "data" ));
                mWeatherData = new Gson ( ).fromJson ( getIntent ( ).getStringExtra ( "data" ), Weather.class );
                initViews ( );
        }

        private void initViews ( ) {
                mWeatherPosition.append ( mWeatherData.getBasic ( ).getCity ( ) + "、" + mWeatherData.getBasic ( ).getCity ( ) );
                mWeatherText.append ( mWeatherData.getNow ( ).getCond ( ).getTxt ( ) );
                mWeatherAll.append (  getIntent ( ).getStringExtra ( "data" ) );
        }
}
