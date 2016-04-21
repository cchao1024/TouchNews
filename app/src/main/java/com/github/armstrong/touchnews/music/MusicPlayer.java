package com.github.armstrong.touchnews.music;

import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;

import com.github.armstrong.touchnews.javaBean.MusicEntity;
import com.github.armstrong.touchnews.javaBean.event.MusicEvent;

import org.greenrobot.eventbus.EventBus;

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

        private List< MusicEntity > mMusicList;


        private MusicEntity mCurMusic;
        private Context mContext;
        public boolean isPause;
        public boolean isPlaying;

        public MusicPlayer ( Context context ) {
                mContext = context;
                initiation ( );
        }

        private void initiation ( ) {
                mMediaPlayer = new MediaPlayer ( );
                mMusicList = new ArrayList<> ( );

                mMediaPlayer.reset ( );
                mMediaPlayer.setAudioStreamType ( AudioManager.STREAM_MUSIC );

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

        public void setMusicList ( List< MusicEntity > musicList ) {
                mMusicList = musicList;
        }

        public void addMusic ( MusicEntity music ) {
                if ( music != null ) {
                        mMusicList.add ( music );
                }
        }

        public int getMusicListCount ( ) {
                return null == mMusicList || mMusicList.isEmpty ( ) ? 0 : mMusicList.size ( );
        }

        public MusicEntity getCurMusic ( ) {
                return mCurMusic;
        }

        public void preparePlay ( ) {
                if ( mMusicList.size ( ) == 0 ) {
                        return;
                }
                mMediaPlayer.reset ( );
                mCurMusic = mMusicList.remove ( 0 );
                String dataSource = mCurMusic.getMusicPath ( ).getUrl ( );
                try {
                        mMediaPlayer.setDataSource ( dataSource );
                        mMediaPlayer.prepareAsync ( );
                } catch ( Exception e ) {
                        e.printStackTrace ( );
                }
                EventBus.getDefault ( ).post ( new MusicEvent ( MusicEvent.MUSIC_TYPE.PREPARE ) );
        }

        public void pause ( ) {

                mMediaPlayer.pause ( );
                isPause = true;
                isPlaying = false;
                EventBus.getDefault ( ).post ( new MusicEvent ( MusicEvent.MUSIC_TYPE.PAUSE ) );
        }

        public void stop ( ) {

                mMediaPlayer.stop ( );
                EventBus.getDefault ( ).post ( new MusicEvent ( MusicEvent.MUSIC_TYPE.STOP ) );
        }

        public void playNext ( ) {
                if ( mMusicList.size ( ) > 0 ) {
                        mCurMusic = mMusicList.remove ( 0 );
                        preparePlay ( );
                }
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
                playNext ( );
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
                mp.start ( );
                mMediaPlayer.start ( );
                EventBus.getDefault ( ).post ( new MusicEvent ( MusicEvent.MUSIC_TYPE.PLAY ) );
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