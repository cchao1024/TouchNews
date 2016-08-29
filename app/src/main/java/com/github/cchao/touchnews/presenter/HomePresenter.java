package com.github.cchao.touchnews.presenter;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.util.Log;

import com.android.volley.VolleyError;
import com.apkfuns.logutils.LogUtils;
import com.github.cchao.touchnews.BaseApplication;
import com.github.cchao.touchnews.contants.Keys;
import com.github.cchao.touchnews.contract.HomeContract;
import com.github.cchao.touchnews.javaBean.Weather;
import com.github.cchao.touchnews.ui.fragment.ChatFragment;
import com.github.cchao.touchnews.ui.fragment.JokeContainerFragment;
import com.github.cchao.touchnews.ui.fragment.NewsContainerFragment;
import com.github.cchao.touchnews.ui.fragment.base.BaseLazyFragment;
import com.github.cchao.touchnews.util.NetRequestUtil;
import com.github.cchao.touchnews.util.UrlUtil;
import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by cchao on 2016/3/31.
 * E-mail:   cchao1024@163.com
 * Description:
 */
public class HomePresenter implements HomeContract.Presenter {
        HomeContract.View mHomeView;
        Gson gson = new Gson ( );
        List< BaseLazyFragment > mFragments;
        Map< String, String > param = new HashMap<> ( );
        Map< String, String > headers = new HashMap<> ( );

        public HomePresenter ( HomeContract.View homeView ) {
                mHomeView = homeView;
        }

        @Override
        public void getFragments ( ) {
                mFragments = new ArrayList<> ( );
                mFragments.add ( new NewsContainerFragment ( ) );
                mFragments.add ( new JokeContainerFragment ( ) );
                mFragments.add ( new ChatFragment ( ) );
//                mFragments.add ( new WxSelectFragment ( ) );
                mHomeView.setFragmentPager ( mFragments );
        }

        @Override
        public void getNavigation ( ) {
                headers.put ( Keys.API_KEY, Keys.WEATHER_KEY );
                param.put ( Keys.CITY, "广州" );
                //获取天气
                NetRequestUtil.getInstance ( ).getJsonWithHeaders ( UrlUtil.URL_WEATHER, param, headers, new NetRequestUtil.RequestListener ( ) {
                        @Override
                        public void onResponse ( JSONObject response ) {
                                Log.e ( "weather", response.toString ( ) );
                                try {
                                        JSONObject jsonObject = new JSONObject ( response.toString ( ) );
                                        JSONObject jsonWeather = jsonObject.getJSONArray ( "HeWeather data service 3.0" ).getJSONObject ( 0 );
                                        String s = jsonWeather.toString ( );
                                        Weather weather = gson.fromJson ( jsonWeather.toString ( ), Weather.class );
                                        mHomeView.setNavigation ( weather );
                                } catch ( JSONException e ) {
                                        e.printStackTrace ( );
                                }
                        }

                        @Override
                        public void onError ( VolleyError error ) {

                        }
                } );
        }

        //获取IP地址
        public String getLocalIpAddress ( ) {
                LocationManager locationManager = ( LocationManager ) BaseApplication.getContext ( ).getSystemService ( Context.LOCATION_SERVICE );
                if ( ActivityCompat.checkSelfPermission ( BaseApplication.getContext ( ), Manifest.permission.ACCESS_FINE_LOCATION ) != PackageManager
                        .PERMISSION_GRANTED &&
                        ActivityCompat.checkSelfPermission ( BaseApplication.getContext ( ), Manifest.permission.ACCESS_COARSE_LOCATION ) != PackageManager.PERMISSION_GRANTED ) {

                        locationManager.requestLocationUpdates ( LocationManager.GPS_PROVIDER, 0, 0, new LocationListener ( ) {
                                @Override
                                public void onLocationChanged ( Location location ) {
                                        LogUtils.e ( "Latitude:" + location.getLatitude ( ) + ", Longitude:"
                                                + location.getLongitude ( ) );
                                }

                                @Override
                                public void onStatusChanged ( String provider, int status, Bundle extras ) {
                                        LogUtils.e ( provider + "status" + status );
                                }

                                @Override
                                public void onProviderEnabled ( String provider ) {
                                        LogUtils.e ( provider );
                                }

                                @Override
                                public void onProviderDisabled ( String provider ) {
                                        LogUtils.e ( provider );
                                }
                        } );
                }
                return null;
        }
}
