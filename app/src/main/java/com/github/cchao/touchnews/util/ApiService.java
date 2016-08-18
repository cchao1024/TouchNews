package com.github.cchao.touchnews.util;


import com.github.cchao.touchnews.javaBean.UserID;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Query;

/**
 * Created by cchao on 2016/8/18.
 * E-mail:   cchao1024@163.com
 * Description: retrofit api 接口
 */
public interface ApiService {
        //身份证信息
        @GET ( "http://apis.baidu.com/apistore/idservice/id" )
        Call< UserID > getID ( @Header ( "apikey" ) String apikey, @Query ( "id" ) String id );
}
