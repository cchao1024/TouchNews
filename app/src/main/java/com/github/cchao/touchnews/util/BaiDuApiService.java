package com.github.cchao.touchnews.util;


import com.github.cchao.touchnews.javaBean.joke.JokeImageRoot;
import com.github.cchao.touchnews.javaBean.joke.JokeTextRoot;
import com.github.cchao.touchnews.javaBean.music.MusicInfoRoot;
import com.github.cchao.touchnews.javaBean.music.MusicPathRoot;
import com.github.cchao.touchnews.javaBean.music.MusicSingerRoot;
import com.github.cchao.touchnews.javaBean.news.NewsItemRoot;

import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by cchao on 2016/8/18.
 * E-mail:   cchao1024@163.com
 * Description: retrofit api 接口
 */
public interface BaiDuApiService {
        //新闻api
        @GET ( "showapi_open_bus/channel_news/search_news" )
        Observable< NewsItemRoot > getNews ( @Header ( "apikey" ) String apikey, @Query ( "channelId" ) String channelID, @Query ( "page" ) String
                page );

        //笑话集
        @GET ( "showapi_open_bus/showapi_joke/joke_text" )
        Observable< JokeTextRoot > getJokeText ( @Header ( "apikey" ) String apikey, @Query ( "page" ) String page );

        //笑话集
        @GET ( "showapi_open_bus/showapi_joke/joke_pic" )
        Observable< JokeImageRoot > getJokeImage ( @Header ( "apikey" ) String apikey, @Query ( "page" ) String page );

        //响应根据关键字获取的歌曲列表
        @GET ( "geekery/music/query" )
        Observable< MusicInfoRoot > getMusicList ( @Header ( "apikey" ) String apikey, @Query ( "s" ) String s );

        //获取Music播放地址
        @GET ( "geekery/music/playinfo" )
        Observable< MusicPathRoot > getMusicPath ( @Header ( "apikey" ) String apikey, @Query ( "hash" ) String hash );

        //歌手信息
        @GET ( "geekery/music/singer" )
        Observable< MusicSingerRoot > getSinger ( @Header ( "apikey" ) String apikey, @Query ( "name" ) String name );


}
