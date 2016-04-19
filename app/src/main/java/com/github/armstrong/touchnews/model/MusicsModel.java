package com.github.armstrong.touchnews.model;

import com.android.volley.VolleyError;
import com.apkfuns.logutils.LogUtils;
import com.github.armstrong.touchnews.javaBean.MusicInfoRoot;
import com.github.armstrong.touchnews.javaBean.music.Data;
import com.github.armstrong.touchnews.javaBean.music.MusicHashRoot;
import com.github.armstrong.touchnews.model.i.IMusicsModel;
import com.github.armstrong.touchnews.presenter.i.IMusicPresenter;
import com.github.armstrong.touchnews.util.NetRequestUtil;
import com.github.armstrong.touchnews.util.UriUtil;
import com.google.gson.Gson;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by cchao on 2016/4/19.
 * E-mail:   cchao1024@163.com
 * Description:
 */
public class MusicsModel implements IMusicsModel {
        Map< String, String > param = new HashMap<> ( );
        Map< String, String > headers = new HashMap<> ( );
        Gson mGson = new Gson ( );
        IMusicPresenter mMusicPresenter;


        public MusicsModel ( IMusicPresenter iMusicPresenter ) {
                mMusicPresenter = iMusicPresenter;
                headers.put ( "apikey", "1bb91df70ccde8148a2c3da582ca9ff2" );
        }

        /**
         * 发送请求获取歌曲的hash值，再请求播放地址
         *
         * @param s 关键字
         */
        public void loadMusicsHashList ( String s ) {
                param.put ( "s", s );
                NetRequestUtil.getInstance ( ).getJsonWithHeaders ( UriUtil.URL_MUSIC_HASH, param, headers, new NetRequestUtil.RequestListener ( ) {
                        @Override
                        public void onResponse ( JSONObject response ) {
                                LogUtils.i (response.toString () );
                                MusicHashRoot musicHashRoot = mGson.fromJson ( response.toString ( ), MusicHashRoot.class );
                                if ( musicHashRoot.getCode ( ) == 0 && musicHashRoot.getData ( ) != null ) {
                                        loadMusicEntityList ( musicHashRoot );
                                }
                        }

                        @Override
                        public void onError ( VolleyError error ) {

                        }
                } );
        }

        /**
         * 获取Music信息数据List
         *
         * @param musicHashRoot 响应的Javabean
         */
        private void loadMusicEntityList ( MusicHashRoot musicHashRoot ) {
                //这是外层包含Page信息的Data
                MusicHashRoot.Data data = musicHashRoot.getData ( );
                //这是歌曲List-Data
                List< Data > dataList = data.getData ( );
                for ( int i = 0 ; i < dataList.size ( ) ; i++ ) {
                        Data musicInfo = dataList.get ( i );
//                        if ( i == 0 ) {
                                //根据hash获取Music播放地址
                                param.clear ( );
                                param.put ( "hash", musicInfo.getHash ( ) );
                                NetRequestUtil.getInstance ( ).getJsonWithHeaders ( UriUtil.URL_MUSIC_INFO, param, headers, new NetRequestUtil.RequestListener ( ) {
                                        @Override
                                        public void onResponse ( JSONObject response ) {
                                                LogUtils.i ( response.toString () );
                                                MusicInfoRoot musicInfoRoot = mGson.fromJson ( response.toString ( ), MusicInfoRoot.class );
                                                if ( musicInfoRoot.getCode ( ) == 0 ) {
                                                        mMusicPresenter.addMusic (musicInfoRoot.getData ());
                                                }
                                        }

                                        @Override
                                        public void onError ( VolleyError error ) {

                                        }
                                } );

//                        }
                }
        }

        /**
         * @param type 关键字
         */
        @Override
        public void loadMusicList ( String type ) {
                loadMusicsHashList ( type );
        }
}
