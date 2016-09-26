package com.github.cchao.touchnews.presenter;

import android.content.Context;

import com.android.volley.VolleyError;
import com.apkfuns.logutils.LogUtils;
import com.github.cchao.touchnews.contract.MusicContract;
import com.github.cchao.touchnews.javaBean.MusicEntity;
import com.github.cchao.touchnews.javaBean.event.MusicEvent;
import com.github.cchao.touchnews.javaBean.music.Data;
import com.github.cchao.touchnews.javaBean.music.MusicInfoRoot;
import com.github.cchao.touchnews.javaBean.music.MusicPathRoot;
import com.github.cchao.touchnews.javaBean.music.MusicSingerRoot;
import com.github.cchao.touchnews.music.MusicPlayer;
import com.github.cchao.touchnews.util.NetRequestUtil;
import com.github.cchao.touchnews.util.UrlUtil;
import com.google.gson.Gson;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by cchao on 2016/4/18.
 * E-mail:   cchao1024@163.com
 * Description:
 */
public class MusicPresenter implements MusicContract.Presenter {
        private MusicContract.View mMusicView;
        //List< MusicPathRoot.Data > mMusicInfoList;
        private List< MusicEntity > mMusicList;
        private MusicPlayer mMusicPlayer;
        private Context mContext;
        private Map< String, String > param = new HashMap<> ( );
        private Map< String, String > headers = new HashMap<> ( );
        private Gson mGson = new Gson ( );

        public MusicPresenter ( Context context, MusicContract.View iMusicView ) {
                mContext = context;
                mMusicView = iMusicView;
//              mMusicInfoList = new ArrayList<> ( );
                mMusicList = new ArrayList<> ( );
                mMusicPlayer = new MusicPlayer ( mContext );
                EventBus.getDefault ( ).register ( this );

                headers.put ( "apikey", "1bb91df70ccde8148a2c3da582ca9ff2" );
        }

        @Subscribe ( threadMode = ThreadMode.MAIN )
        public void onMusicEvent ( MusicEvent musicEvent ) {
                switch ( musicEvent.getType ( ) ) {
                        case PLAY:
                                mMusicView.onMusicPlay ( );
                                break;
                        case PREPARE:
                                mMusicView.onMusicPrepare ( mMusicPlayer.getCurMusic ( ) );
                                mMusicView.setAlbum ( );
                                break;
                        case PAUSE:
                                mMusicView.onMusicPause ( );
                                break;
                        case RESUME_PALY:
                                mMusicView.onResumePlay ( );
                                break;
                }
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
                EventBus.getDefault ( ).unregister ( this );
                mMusicPlayer.onDestroy ( );
        }

        @Override
        public void getMusic ( String musicName ) {
                mMusicList.clear ( );
                loadMusicsHashList ( musicName, new NetRequestUtil.RequestListener ( ) {
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
                        public void onError ( VolleyError error ) {

                        }
                } );
        }

        /**
         * @param key 搜索关键字
         */
        @Override
        public void searchMusic ( String key ) {
                loadMusicsHashList ( key, new NetRequestUtil.RequestListener ( ) {
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


        public void addMusicListToPlayer ( ) {
//                if ( music != null ) {
//                        mMusicInfoList.add ( music );
                mMusicPlayer.setMusicList ( mMusicList );
                mMusicPlayer.play ( );
//                }
                // 如果插入第一首歌，就播放
//                if ( mMusicInfoList.size ( ) == 1 ) {
//                        mMusicPlayer.preparePlay ( );
//                }
        }

        private void getMusicPath ( Data musicInfo, final MusicEntity musicEntity, final int finalI ) {
                loadMusicPath ( musicInfo.getHash ( ), new NetRequestUtil.RequestListener ( ) {
                        @Override
                        public void onResponse ( JSONObject response ) {
                                LogUtils.i ( response.toString ( ) );
                                MusicPathRoot musicPathRoot = mGson.fromJson ( response.toString ( ), MusicPathRoot.class );
                                if ( musicPathRoot.getCode ( ) == 0 ) {
//                                                                mMusicPresenter.addMusic ( musicPathRoot.getData ( ) );
                                        musicEntity.setMusicPath ( musicPathRoot.getData ( ) );
                                        //如果获取到第一首歌的播放地址，就播放
                                        mMusicList.add ( musicEntity );
                                        if ( finalI == 0 ) {
                                                addMusicListToPlayer ( );
                                        }
                                }
                        }

                        @Override
                        public void onError ( VolleyError error ) {
                        }
                } );
        }

        private void getSingerAlbum ( Data musicInfo, final MusicEntity musicEntity, final int i ) {
                loadSingerAlbum ( musicInfo.getSingername ( ), new NetRequestUtil.RequestListener ( ) {
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
                        public void onError ( VolleyError error ) {
                        }
                } );
        }

        /**
         * 发送请求获取歌曲的hash值，再请求播放地址
         *
         * @param s 关键字
         */
        public void loadMusicsHashList ( String s, NetRequestUtil.RequestListener requestListener ) {
                param.put ( "s", s );
                NetRequestUtil.getInstance ( ).getJsonWithHeaders ( UrlUtil.URL_MUSIC_HASH, param, headers, requestListener );
        }


        public void loadSingerAlbum ( String singerName, NetRequestUtil.RequestListener requestListener ) {
                param.clear ( );
                param.put ( "name", singerName );
                NetRequestUtil.getInstance ( ).getJsonWithHeaders ( UrlUtil.URL_MUSIC_SINGER, param, headers, requestListener );
        }


        /**
         * 获取Music信息数据List
         *
         * @param hash music- Hash 值
         */
        public void loadMusicPath ( String hash, NetRequestUtil.RequestListener requestListener ) {
//                这是外层包含Page信息的Data
//                MusicInfoRoot.Data data = MusicInfoRoot.getData ( );
                //这是歌曲List-Data
//                List< Data > dataList = data.getData ( );
//                for ( int i = 0 ; i < dataList.size ( ) ; i++ ) {
//                        Data musicInfo = dataList.get ( i );
//                        if ( i == 0 ) {
                //根据hash获取Music播放地址
                param.clear ( );
                param.put ( "hash", hash );
                NetRequestUtil.getInstance ( ).getJsonWithHeaders ( UrlUtil.URL_MUSIC_INFO, param, headers, requestListener );
        }
}
