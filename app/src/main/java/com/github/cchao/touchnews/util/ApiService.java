package com.github.cchao.touchnews.util;


import com.github.cchao.touchnews.javaBean.WxArticle;

import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by cchao on 2016/8/18.
 * E-mail:   cchao1024@163.com
 * Description: retrofit api 接口
 */
public interface ApiService {
        //wx
        @GET ( "showapi_open_bus/weixin/weixin_article_list" )
        Observable< WxArticle > getID ( @Header ( "apikey" ) String apikey, @Query ( "page" ) String page );
}
