package com.github.cchao.touchnews.model;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;

import com.apkfuns.logutils.LogUtils;
import com.github.cchao.touchnews.BaseApplication;
import com.github.cchao.touchnews.model.i.IHomeModel;
import com.github.cchao.touchnews.presenter.i.IHomePresenter;
import com.github.cchao.touchnews.ui.fragment.ChatFragment;
import com.github.cchao.touchnews.ui.fragment.JokeContainerFragment;
import com.github.cchao.touchnews.ui.fragment.MusicFragment;
import com.github.cchao.touchnews.ui.fragment.NewsContainerFragment;
import com.github.cchao.touchnews.ui.fragment.base.BaseLazyFragment;
import com.github.cchao.touchnews.util.NetRequestUtil;
import com.github.cchao.touchnews.util.UriUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by cchao on 2016/3/31.
 * E-mail:   cchao1024@163.com
 * Description:
 */
public class HomeModel implements IHomeModel {
        IHomePresenter mHomePresenter;
        List< BaseLazyFragment > mFragments;
        Map< String, String > param = new HashMap<> ( );
        Map< String, String > headers = new HashMap<> ( );

        public HomeModel ( IHomePresenter homePresenter ) {
                mHomePresenter = homePresenter;
                headers.put ( "apikey", "1bb91df70ccde8148a2c3da582ca9ff2" );
        }

        @Override
        public List getFragments ( ) {
                mFragments = new ArrayList<> ( );
                mFragments.add ( new NewsContainerFragment ( ) );
                mFragments.add ( new JokeContainerFragment ( ) );
                mFragments.add ( new MusicFragment ( ) );
                mFragments.add ( new ChatFragment ( ) );
                return mFragments;
        }

        @Override
        public void loadNavigation ( final NetRequestUtil.RequestListener requestListener ) {
                param.put ( "city", "广州");
                //获取天气
                NetRequestUtil.getInstance ( ).getJsonWithHeaders ( UriUtil.URL_WEATHER, param, headers, requestListener );
                //根据IP地址获取城市名>获取天气数据
                /*NetRequestUtil.getInstance ( ).getJson ( UriUtil.URL_CITY, null, new NetRequestUtil.RequestListener ( ) {
                        @Override
                        public void onResponse ( JSONObject response ) {
                                String responseStr=response.toString ();
                                LogUtils.i ( response.toString ( ) );
                                //获取城市名
//                             param.put ( "city", response.getString ( "country" ) );
                                param.put ( "city", "广州");
//                                responseStr.s
                                //获取天气
                                NetRequestUtil.getInstance ( ).getJsonWithHeaders ( UriUtil.URL_WEATHER, param, headers, requestListener );
                        }

                        @Override
                        public void onError ( VolleyError error ) {

                        }
                } );*/
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
                                        LogUtils.e ( provider+"status"+status );
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
