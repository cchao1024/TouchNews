package com.github.armstrong.touchnews.model.i;

import android.content.Loader;

import com.github.armstrong.touchnews.util.NetRequestUtil;

/**
 * Created by cchao on 2016/4/19.
 * E-mail:   cchao1024@163.com
 * Description:
 */
public interface IMusicsModel {
        void loadMusicsHashList ( String type, NetRequestUtil.RequestListener requestListener);
        void loadSingerAlbum ( String singerName, NetRequestUtil.RequestListener requestListener);
        void loadMusicPath ( String hash, NetRequestUtil.RequestListener requestListener);
}
