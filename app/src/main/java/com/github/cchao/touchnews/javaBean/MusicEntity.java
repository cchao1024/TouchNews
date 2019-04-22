package com.github.cchao.touchnews.javaBean;

import com.github.cchao.touchnews.javaBean.music.Data;
import com.github.cchao.touchnews.javaBean.music.MusicPathRoot;
import com.github.cchao.touchnews.javaBean.music.MusicSingerRoot;

/**
 * Created by cchao on 2016/4/21.
 * E-mail:   cchao1024@163.com
 * Description:
 */
public class MusicEntity {
    private Data mMusicInfo;
    private MusicPathRoot.Data mMusicPath;
    private MusicSingerRoot.Data mMusicSinger;

    public MusicEntity(Data data) {
        mMusicInfo = data;
    }

    public MusicPathRoot.Data getMusicPath() {
        return mMusicPath;
    }

    public void setMusicPath(MusicPathRoot.Data musicPath) {
        mMusicPath = musicPath;
    }

    public MusicSingerRoot.Data getMusicSinger() {
        return mMusicSinger;
    }

    public void setMusicSinger(MusicSingerRoot.Data musicSinger) {
        mMusicSinger = musicSinger;
    }

    public Data getMusicInfo() {

        return mMusicInfo;
    }

    public void setMusicInfo(Data musicInfo) {
        mMusicInfo = musicInfo;
    }
}
