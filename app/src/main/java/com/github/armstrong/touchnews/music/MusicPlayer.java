package com.github.armstrong.touchnews.music;

import android.content.Context;
import android.content.Intent;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;

import com.github.armstrong.touchnews.javaBean.MusicEntity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by cchao on 2016/4/18.
 * E-mail:   cchao1024@163.com
 * Description:  音乐播放器
 */
public class MusicPlayer implements MediaPlayer.OnCompletionListener, MediaPlayer.OnErrorListener, MediaPlayer.OnBufferingUpdateListener, MediaPlayer.OnPreparedListener {

        private final static String TAG = MusicPlayer.class.getSimpleName ( );

        private final static long SLEEP_TIME = 1000;

        private MediaPlayer mMediaPlayer;

        private List<MusicEntity > mMusicList;
        private MusicEntity mCurMusic;
        private Context mContext;

        public MusicPlayer ( Context context ) {
                initiation ( context );
        }

        private void initiation ( Context context ) {
                mContext = context;

                mMediaPlayer = new MediaPlayer ( );
                mMusicList = new ArrayList<MusicEntity> ( );

                mMediaPlayer.setOnErrorListener ( this );
                mMediaPlayer.setOnPreparedListener ( this );
                mMediaPlayer.setOnCompletionListener ( this );
                mMediaPlayer.setOnBufferingUpdateListener ( this );

        }

        public void exit ( ) {
                mMediaPlayer.reset ( );
                mMediaPlayer.release ( );
                mMusicList.clear ( );
        }

        public void setMusicList ( List<MusicEntity> musicList ) {
                mMusicList=musicList;
        }

        public int getMusicListCount ( ) {
                return null == mMusicList || mMusicList.isEmpty ( ) ? 0 : mMusicList.size ( );
        }
        public void play ( ) {
                mMediaPlayer.reset ( );
                mMediaPlayer.setAudioStreamType ( AudioManager.STREAM_MUSIC );

                String dataSource = mCurMusic.getUrl ( );
                try {
                        mMediaPlayer.setDataSource ( dataSource );
                        mMediaPlayer.prepareAsync ( );
                } catch ( Exception e ) {
                        e.printStackTrace ( );
                }
        }
        public void pause ( ) {
                /*if ( mPlayState != MusicPlayState.MPS_PLAYING ) {
                        return;
                }*/

                mMediaPlayer.pause ( );
//                mPlayState = MusicPlayState.MPS_PAUSE;
        }

        public void stop ( ) {
                /*if ( mPlayState != MusicPlayState.MPS_PLAYING && mPlayState != MusicPlayState.MPS_PAUSE ) {
                        return;
                }*/

                mMediaPlayer.stop ( );
//                mPlayState = MusicPlayState.MPS_STOP;
        }

        public void playNext ( ) {
                mCurMusic=mMusicList.remove ( 0 );
                play ();
        }

        public void seekTo ( int rate ) {
             /*   if ( mPlayState == MusicPlayState.MPS_LIST_EMPTY ) {
                        return;
                }*/

//                int r = reviceSeekValue ( rate );
                int time = mMediaPlayer.getDuration ( );
//                int curTime = ( int ) ( ( float ) r / 100 * time );

//                mMediaPlayer.seekTo ( curTime );
        }



        @Override
        public void onCompletion ( MediaPlayer mp ) {
        }

        @Override
        public boolean onError ( MediaPlayer mp, int what, int extra ) {
                return false;
        }

        @Override
        public void onBufferingUpdate ( MediaPlayer mp, int percent ) {

        }

        @Override
        public void onPrepared ( MediaPlayer mp ) {

        }

      /*  @Override
        public void onBufferingUpdate ( MediaPlayer mp, int percent ) {
                TLog.d ( TAG, "second percent --> " + percent );
                if ( percent < 100 ) {
                        Intent intent = new Intent ( );
                        intent.setAction ( Constants.ACTION_MUSIC_SECOND_PROGRESS_BROADCAST );
                        intent.putExtra ( Constants.KEY_MUSIC_SECOND_PROGRESS, percent );
                        mContext.sendBroadcast ( intent );
                }
        }*/

}