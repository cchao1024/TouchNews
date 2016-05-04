package com.github.armstrong.touchnews.presenter;

import android.content.Context;

import com.android.volley.VolleyError;
import com.apkfuns.logutils.LogUtils;
import com.github.armstrong.touchnews.javaBean.MusicEntity;
import com.github.armstrong.touchnews.javaBean.event.MusicEvent;
import com.github.armstrong.touchnews.javaBean.music.MusicPathRoot;
import com.github.armstrong.touchnews.javaBean.music.Data;
import com.github.armstrong.touchnews.javaBean.music.MusicInfoRoot;
import com.github.armstrong.touchnews.javaBean.music.MusicSingerRoot;
import com.github.armstrong.touchnews.model.MusicsModel;
import com.github.armstrong.touchnews.model.i.IMusicsModel;
import com.github.armstrong.touchnews.music.MusicPlayer;
import com.github.armstrong.touchnews.presenter.i.IMusicPresenter;
import com.github.armstrong.touchnews.util.NetRequestUtil;
import com.github.armstrong.touchnews.view.IMusicView;
import com.google.gson.Gson;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by cchao on 2016/4/18.
 * E-mail:   cchao1024@163.com
 * Description:
 */
public class MusicPresenter implements IMusicPresenter, NetRequestUtil.RequestListener {
        IMusicView mMusicView;
        IMusicsModel mMusicsModel;
        //        List< MusicPathRoot.Data > mMusicInfoList;
        List< MusicEntity > mMusicList;
        Gson mGson = new Gson ( );
        MusicPlayer mMusicPlayer;
        Context mContext;

        public MusicPresenter ( Context context, IMusicView iMusicView ) {
                mContext = context;
                mMusicView = iMusicView;
//              mMusicInfoList = new ArrayList<> ( );
                mMusicList = new ArrayList<> ( );
                mMusicPlayer = new MusicPlayer ( mContext );
                mMusicsModel = new MusicsModel ( this );
                mMusicsModel.loadMusicsHashList ( "经典", this );
                EventBus.getDefault ( ).register ( this );
        }

        @Subscribe ( threadMode = ThreadMode.MAIN )
        public void onMusicEvent ( MusicEvent musicEvent ) {
                switch ( musicEvent.getType ( ) ) {
                        case PLAY:
                                mMusicView.onMusicPlay ( );
                                break;
                        case PREPARE:
                                mMusicView.onMusicPrepare ( mMusicPlayer.getCurMusic ( ) );
                                mMusicView.setAlbum();
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
                                final int finalI = i;
                                mMusicsModel.loadMusicPath ( musicInfo.getHash ( ), new NetRequestUtil.RequestListener ( ) {
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

                        //获取歌手图片信息
                        mMusicsModel.loadSingerAlbum ( musicInfo.getSingername ( ), new NetRequestUtil.RequestListener ( ) {
                                @Override
                                public void onResponse ( JSONObject response ) {
                                        LogUtils.i ( response.toString ( ) );
                                        MusicSingerRoot musicSingerRoot = mGson.fromJson ( response.toString ( ), MusicSingerRoot.class );
                                        if ( musicSingerRoot.getCode ( ) == 0 ) {
//                                                                mMusicPresenter.addMusic ( musicInfoRoot.getData ( ) );
                                                musicEntity.setMusicSinger ( musicSingerRoot.getData ( ) );
                                                if ( finalI == 0 ) {
                                                mMusicView.setAlbum ( );}
                                        }
                                }

                                @Override
                                public void onError ( VolleyError error ) {
                                }
                        } );

                }

        }

}

        @Override
        public void onError ( VolleyError error ) {

        }
}
