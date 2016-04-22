package com.github.armstrong.touchnews.javaBean.event;

import android.drm.DrmStore;

/**
 * Created by cchao on 2016/4/20.
 * E-mail:   cchao1024@163.com
 * Description:
 */
public class MusicEvent {
        public  enum MUSIC_TYPE{
                PREPARE,PLAY,PAUSE, RESUME_PALY,STOP
        }
        private MUSIC_TYPE type;
        public MusicEvent (MUSIC_TYPE type ) {
                this.type=type;
        }

        public MUSIC_TYPE getType ( ) {
                return type;
        }

        public void setType ( MUSIC_TYPE type ) {
                this.type = type;
        }
}
