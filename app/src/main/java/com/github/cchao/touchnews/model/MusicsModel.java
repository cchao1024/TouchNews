package com.github.cchao.touchnews.model;

import com.github.cchao.touchnews.model.i.IMusicsModel;
import com.github.cchao.touchnews.presenter.i.IMusicPresenter;
import com.github.cchao.touchnews.util.NetRequestUtil;
import com.github.cchao.touchnews.util.UrlUtil;
import com.google.gson.Gson;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by cchao on 2016/4/19.
 * E-mail:   cchao1024@163.com
 * Description:
 */
public class MusicsModel implements IMusicsModel {
        Map< String, String > param = new HashMap<> ( );
        Map< String, String > headers = new HashMap<> ( );
        Gson mGson = new Gson ( );
        IMusicPresenter mMusicPresenter;


        public MusicsModel ( IMusicPresenter iMusicPresenter ) {
                mMusicPresenter = iMusicPresenter;
                headers.put ( "apikey", "1bb91df70ccde8148a2c3da582ca9ff2" );
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


        @Override
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
        @Override
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

//                        }
        }

}
