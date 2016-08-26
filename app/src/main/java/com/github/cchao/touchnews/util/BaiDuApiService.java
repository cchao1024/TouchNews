package com.github.cchao.touchnews.util;


import com.github.cchao.touchnews.javaBean.WxArticle;
import com.github.cchao.touchnews.javaBean.joke.JokeImageRoot;
import com.github.cchao.touchnews.javaBean.joke.JokeTextRoot;

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
        //wx
        @GET ( "showapi_open_bus/weixin/weixin_article_list" )
        Observable< WxArticle > getWxArticle ( @Header ( "apikey" ) String apikey, @Query ( "page" ) String page );

        //笑话集
        @GET ( "showapi_open_bus/showapi_joke/joke_text" )
        Observable< JokeTextRoot > getJokeText ( @Header ( "apikey" ) String apikey, @Query ( "page" ) String page );

        //笑话集
        @GET ( "showapi_open_bus/showapi_joke/joke_pic" )
        Observable< JokeImageRoot > getJokeImage ( @Header ( "apikey" ) String apikey, @Query ( "page" ) String page );
}
