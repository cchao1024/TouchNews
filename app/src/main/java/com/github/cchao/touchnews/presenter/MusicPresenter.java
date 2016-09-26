package com.github.cchao.touchnews.presenter;

import android.content.Context;

import com.android.volley.VolleyError;
import com.apkfuns.logutils.LogUtils;
import com.github.cchao.touchnews.BaseApplication;
import com.github.cchao.touchnews.contants.Keys;
import com.github.cchao.touchnews.contract.MusicContract;
import com.github.cchao.touchnews.javaBean.MusicEntity;
import com.github.cchao.touchnews.javaBean.music.Data;
import com.github.cchao.touchnews.javaBean.music.MusicInfoRoot;
import com.github.cchao.touchnews.javaBean.music.MusicPathRoot;
import com.github.cchao.touchnews.javaBean.music.MusicSingerRoot;
import com.github.cchao.touchnews.music.MusicPlayer;
import com.github.cchao.touchnews.util.NetRequestUtil;
import com.github.cchao.touchnews.util.UrlUtil;
import com.google.gson.Gson;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

/**
 * Created by cchao on 2016/4/18.
 * E-mail:   cchao1024@163.com
 * Description: 音乐
 */
public class MusicPresenter implements MusicContract.Presenter {
        private MusicContract.View mMusicView;
        private List< MusicEntity > mMusicList = new ArrayList<> ( );
        private Map< String, String > param = new HashMap<> ( );
        private Map< String, String > headers = new HashMap<> ( );
        private MusicPlayer mMusicPlayer;
        private Gson mGson = new Gson ( );

        public MusicPresenter ( Context context, MusicContract.View iMusicView ) {
                mMusicView = iMusicView;
                mMusicPlayer = new MusicPlayer ( context );
//                EventBus.getDefault ( ).register ( this );

                headers.put ( Keys.API_KEY, "1bb91df70ccde8148a2c3da582ca9ff2" );
                setMusicListener ( );
        }

        /**
         * 设置music 状态监听器
         */
        private void setMusicListener ( ) {
                mMusicPlayer.setPlayerStateListener ( new MusicPlayer.MusicPlayerStateListener ( ) {
                        @Override
                        public void onPrepared ( ) {
                                mMusicView.onMusicPrepare ( mMusicPlayer.getCurMusic ( ) );
                                mMusicView.setAlbum ( );
                        }

                        @Override
                        public void onResumePlay ( ) {
                                mMusicView.onResumePlay ( );
                        }

                        @Override
                        public void onPlay ( ) {
                                mMusicView.onMusicPlay ( );
                        }

                        @Override
                        public void onPause ( ) {
                                mMusicView.onMusicPause ( );
                        }

                        @Override
                        public void onStop ( ) {}
                } );
        }
        @Override
        public void onPlay ( ) {
                mMusicPlayer.play ( );
        }

        @Override
        public void onNext ( ) {
                mMusicPlayer.playNext ( );
        }

        @Override
        public void onDestroy ( ) {
                mMusicList.clear ( );
//                EventBus.getDefault ( ).unregister ( this );
                mMusicPlayer.onDestroy ( );
        }

        /**
         * 响应根据关键字获取的歌曲列表
         */
        @Override
        public void getMusic ( String musicName ) {
                mMusicList.clear ( );

                BaseApplication.mBaiDuApiService.getMusicList ( Keys.WEATHER_KEY, musicName )
                        .subscribeOn ( Schedulers.newThread ( ) )
                        .observeOn ( AndroidSchedulers.mainThread ( ) )
                        .subscribe ( new Action1< MusicInfoRoot > ( ) {
                                @Override
                                public void call ( MusicInfoRoot musicInfoRoot ) {
                                        if ( musicInfoRoot.getCode ( ) == 0 && musicInfoRoot.getData ( ) != null ) {
//                        这是外层包含Page信息的Data
                                                MusicInfoRoot.Data data = musicInfoRoot.getData ( );
                                                //这是歌曲信息HashList-Data
                                                List< Data > dataList = data.getData ( );
                                                for ( int i = 0 ; i < dataList.size ( ) ; i++ ) {
                                                        Data musicInfo = dataList.get ( i );
                                                        final MusicEntity musicEntity = new MusicEntity ( musicInfo );
                                                        //获取到播放地址就加入到待播放列表 - 图片等获取到地址再显示
                                                        //获取播放路径
                                                        getMusicPath ( musicInfo, musicEntity, i );
                                                        //获取歌手图片信息
                                                        getSingerAlbum ( musicInfo, musicEntity, i );
                                                }
                                        }
                                }
                        } );
                /*param.put ( "s", musicName );
                NetRequestUtil.getInstance ( ).getJsonWithHeaders ( UrlUtil.URL_MUSIC_HASH, param, headers, new NetRequestUtil.RequestListener ( ) {

                        @Override
                        public void onResponse ( JSONObject response ) {
                                LogUtils.i ( response.toString ( ) );
                                MusicInfoRoot musicInfoRoot = mGson.fromJson ( response.toString ( ), MusicInfoRoot.class );
                                if ( musicInfoRoot.getCode ( ) == 0 && musicInfoRoot.getData ( ) != null ) {
//                        这是外层包含Page信息的Data
                                        MusicInfoRoot.Data data = musicInfoRoot.getData ( );
                                        //这是歌曲信息HashList-Data
                                        List< Data > dataList = data.getData ( );
                                        for ( int i = 0 ; i < dataList.size ( ) ; i++ ) {
                                                Data musicInfo = dataList.get ( i );
                                                final MusicEntity musicEntity = new MusicEntity ( musicInfo );
                                                //获取到播放地址就加入到待播放列表 - 图片等获取到地址再显示
                                                //获取播放路径
                                                getMusicPath ( musicInfo, musicEntity, i );
                                                //获取歌手图片信息
                                                getSingerAlbum ( musicInfo, musicEntity, i );
                                        }
                                }
                        }

                        @Override
                        public void onError ( VolleyError error ) { }
                } );*/
        }

        /**
         * 发送请求获取歌曲的hash值，再请求播放地址
         *
         * @param key 搜索关键字
         */
        @Override
        public void searchMusic ( String key ) {
                param.put ( "s", key );
                NetRequestUtil.getInstance ( ).getJsonWithHeaders ( UrlUtil.URL_MUSIC_HASH, param, headers, new NetRequestUtil.RequestListener ( ) {
                        /**
                         * @param response 响应根据关键字获取的歌曲列表
                         */
                        @Override
                        public void onResponse ( JSONObject response ) {
                                LogUtils.i ( response.toString ( ) );
                                MusicInfoRoot musicInfoRoot = mGson.fromJson ( response.toString ( ), MusicInfoRoot.class );
                                if ( musicInfoRoot.getCode ( ) == 0 && musicInfoRoot.getData ( ) != null ) {
//                        这是外层包含Page信息的Data
                                        MusicInfoRoot.Data data = musicInfoRoot.getData ( );
                                        //这是歌曲信息HashList-Data
                                        List< Data > dataList = data.getData ( );
                                        List< String > searchResult = new ArrayList< String > ( );
                                        for ( int i = 0 ; i < dataList.size ( ) ; i++ ) {
                                                Data musicInfo = dataList.get ( i );
                                                final MusicEntity musicEntity = new MusicEntity ( musicInfo );
                                                searchResult.add ( musicEntity.getMusicInfo ( ).getFilename ( ) );
                                        }
                                        mMusicView.updateSearchList ( searchResult );
                                }
                        }

                        @Override
                        public void onError ( VolleyError error ) {

                        }
                } );
        }

        /**
         * 获取Music信息数据List
         * <p>
         * music- Hash 值
         */
        private void getMusicPath ( Data musicInfo, final MusicEntity musicEntity, final int finalI ) {

                //根据hash获取Music播放地址
                param.clear ( );
                param.put ( "hash", musicInfo.getHash ( ) );
                NetRequestUtil.getInstance ( ).getJsonWithHeaders ( UrlUtil.URL_MUSIC_INFO, param, headers, new NetRequestUtil.RequestListener ( ) {
                        @Override
                        public void onResponse ( JSONObject response ) {
                                LogUtils.i ( response.toString ( ) );
                                MusicPathRoot musicPathRoot = mGson.fromJson ( response.toString ( ), MusicPathRoot.class );
                                if ( musicPathRoot.getCode ( ) == 0 ) {
                                        musicEntity.setMusicPath ( musicPathRoot.getData ( ) );
                                        //如果获取到第一首歌的播放地址，就播放
                                        mMusicList.add ( musicEntity );
                                        if ( finalI == 0 ) {
                                                mMusicPlayer.setMusicList ( mMusicList );
                                                mMusicPlayer.play ( );
                                        }
                                }
                        }

                        @Override
                        public void onError ( VolleyError error ) {
                        }
                } );
        }

        /**
         * 获取专辑头像
         *
         * @param musicInfo mu
         */
        private void getSingerAlbum ( Data musicInfo, final MusicEntity musicEntity, final int i ) {
                param.clear ( );
                param.put ( "name", musicInfo.getSingername ( ) );
                NetRequestUtil.getInstance ( ).getJsonWithHeaders ( UrlUtil.URL_MUSIC_SINGER, param, headers, new NetRequestUtil.RequestListener ( ) {
                        @Override
                        public void onResponse ( JSONObject response ) {
                                LogUtils.i ( response.toString ( ) );
                                MusicSingerRoot musicSingerRoot = mGson.fromJson ( response.toString ( ), MusicSingerRoot.class );
                                if ( musicSingerRoot.getCode ( ) == 0 ) {
//                                                                mMusicPresenter.addMusic ( musicInfoRoot.getData ( ) );
                                        musicEntity.setMusicSinger ( musicSingerRoot.getData ( ) );
                                        if ( i == 0 ) {
                                                mMusicView.setAlbum ( );
                                        }
                                }
                        }
                        @Override
                        public void onError ( VolleyError error ) {}
                } );
        }
}
