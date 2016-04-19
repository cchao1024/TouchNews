package com.github.armstrong.touchnews.presenter;

import android.content.Context;
import android.media.MediaPlayer;

import com.github.armstrong.touchnews.javaBean.MusicInfoRoot;
import com.github.armstrong.touchnews.model.MusicsModel;
import com.github.armstrong.touchnews.model.i.IMusicsModel;
import com.github.armstrong.touchnews.music.MusicPlayer;
import com.github.armstrong.touchnews.presenter.i.IMusicPresenter;
import com.github.armstrong.touchnews.view.IMusicView;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by cchao on 2016/4/18.
 * E-mail:   cchao1024@163.com
 * Description:
 */
public class MusicPresenter implements IMusicPresenter {
        IMusicView mMusicView;
        IMusicsModel mMusicsModel;
        Gson mGson = new Gson ( );
        List< MusicInfoRoot.Data > mMusicInfoList;
        MusicPlayer mMusicPlayer;
        Context mContext;

        public MusicPresenter (Context context, IMusicView iMusicView ) {
                mContext=context;
                mMusicView = iMusicView;
                mMusicsModel = new MusicsModel ( this );
                mMusicsModel.loadMusicList ( "流行" );
                mMusicInfoList = new ArrayList< MusicInfoRoot.Data > ( );
                mMusicPlayer=new MusicPlayer ( mContext);
        }

        @Override
        public void onPlay ( ) {

        }

        @Override
        public void onNext ( ) {

        }

        @Override
        public void addMusic ( MusicInfoRoot.Data music ) {
                if ( music != null ) {
                        mMusicPlayer.addMusic ( music );
                }
                // 如果插入第一首歌，就播放
                if(mMusicInfoList.size ()==1){
                        mMusicPlayer.play ();
                }
        }
}
