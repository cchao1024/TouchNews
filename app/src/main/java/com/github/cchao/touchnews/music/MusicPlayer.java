package com.github.cchao.touchnews.music;

import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;

import com.github.cchao.touchnews.javaBean.MusicEntity;
import com.github.cchao.touchnews.javaBean.event.MusicEvent;
import com.github.cchao.touchnews.util.ToastUtil;

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
        public boolean isPause;
        public MusicPlayerStateListener mPlayerStateListener;
        private MediaPlayer mMediaPlayer;
        private List< MusicEntity > mMusicList;
        private MusicEntity mCurMusic;
        private Context mContext;

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


        public void setMusicList ( List< MusicEntity > musicList ) {
                mMusicList = musicList;
                mMediaPlayer.reset ( );
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

        /**
         * 用户点击播放键
         */
        public void play ( ) {
                if ( isPause ) {
                        //暂停后再播放
                        mMediaPlayer.start ( );
                        isPause = false;
                        EventBus.getDefault ( ).post ( new MusicEvent ( MusicEvent.MUSIC_TYPE.RESUME_PALY ) );
                        if ( mPlayerStateListener != null ) {
                                mPlayerStateListener.onResumePlay ( );
                        }
                } else if ( isPlaying ( ) ) {
                        this.pauseM ( );
                } else {
                        if ( mMusicList.size ( ) > 0 ) {
                                //播一首移除一首
                                mCurMusic = mMusicList.remove ( 0 );
                                String dataSource = mCurMusic.getMusicPath ( ).getUrl ( );
                                mMediaPlayer.reset ( );
                                try {
                                        mMediaPlayer.setDataSource ( dataSource );
                                        mMediaPlayer.prepareAsync ( );
                                } catch ( Exception e ) {
                                        e.printStackTrace ( );
                                }
                                if ( mPlayerStateListener != null ) {
                                        mPlayerStateListener.onPrepared ( );
                                }
                                EventBus.getDefault ( ).post ( new MusicEvent ( MusicEvent.MUSIC_TYPE.PREPARE ) );
                        }

                }
        }

        public void pauseM ( ) {
                mMediaPlayer.pause ( );
                isPause = true;
                EventBus.getDefault ( ).post ( new MusicEvent ( MusicEvent.MUSIC_TYPE.PAUSE ) );
                if ( mPlayerStateListener != null ) {
                        mPlayerStateListener.onPause ( );
                }
        }


        public void stop ( ) {
                mMediaPlayer.stop ( );
                EventBus.getDefault ( ).post ( new MusicEvent ( MusicEvent.MUSIC_TYPE.STOP ) );
                if ( mPlayerStateListener != null ) {
                        mPlayerStateListener.onStop ( );
                }
        }

        /**
         * 播放下一首
         */
        public void playNext ( ) {
                //列表如果还有歌曲、播放下一首
                if ( mMusicList.size ( ) > 0 ) {
//                        mCurMusic = mMusicList.remove ( 0 );
                        mMediaPlayer.stop ( );
                        play ( );
                } else {
                        ToastUtil.showShortToast ( null, "没有下一首了" );
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

        /**
         * 播放完播下一首
         *
         * @param mp
         */
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

        public void onDestroy ( ) {
                mMediaPlayer.reset ( );
                mMediaPlayer.release ( );
                mCurMusic = null;
                mMusicList.clear ( );
        }

        /**
         * @return 是否播放
         */
        public boolean isPlaying ( ) {
                return mMediaPlayer.isPlaying ( );
        }

        @Override
        public void onPrepared ( MediaPlayer mp ) {
                mp.start ( );
                isPause = false;
                EventBus.getDefault ( ).post ( new MusicEvent ( MusicEvent.MUSIC_TYPE.PLAY ) );
                if ( mPlayerStateListener != null ) {
                        mPlayerStateListener.onPlay ( );
                }
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
        //设置状态监听器
        public void setPlayerStateListener ( MusicPlayerStateListener musicPlayerStateListener ) {
                mPlayerStateListener = musicPlayerStateListener;
        }

        //监听器
        public interface MusicPlayerStateListener {
                void onPrepared ( );

                void onResumePlay ( );

                void onPlay ( );

                void onPause ( );

                void onStop ( );
        }
}