package com.github.cchao.touchnews.manager;

import com.github.cchao.touchnews.util.BaiDuApiService;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by cchao on 2016/8/19.
 * E-mail:   cchao1024@163.com
 * Description:
 */
public class ApiServiceManager {
        private static ApiServiceManager ourInstance = new ApiServiceManager ( );

        public static ApiServiceManager getInstance ( ) {
                return ourInstance;
        }

        private ApiServiceManager ( ) {}

        /**
         * 获取api
         *
         * @param baseUrl baseUrl
         * @return
         */
        public static BaiDuApiService getApiService ( String baseUrl ) {
                Retrofit retrofit = new Retrofit.Builder()
                        .addConverterFactory( GsonConverterFactory.create())
                        .addCallAdapterFactory( RxJavaCallAdapterFactory.create())
                        .baseUrl ( baseUrl )
                        .build();
                return retrofit.create(BaiDuApiService.class);
        }
}
