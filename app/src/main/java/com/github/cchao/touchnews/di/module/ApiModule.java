package com.github.cchao.touchnews.di.module;

import android.support.annotation.NonNull;

import com.github.cchao.touchnews.util.BaiDuApiService;
import com.github.cchao.touchnews.util.UrlUtil;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by cchao on 2016/8/23.
 * E-mail:   cchao1024@163.com
 * Description: api module
 */
@Module
public class ApiModule {
        /**
         * @param baseUrl baseUrl
         * @return Retrofit 对象
         */
        private Retrofit getApiService ( @NonNull String baseUrl ) {
                return new Retrofit.Builder ( )
                        .addConverterFactory ( GsonConverterFactory.create ( ) )
                        .addCallAdapterFactory ( RxJavaCallAdapterFactory.create ( ) )
                        .baseUrl ( baseUrl )
                        .build ( );
        }

        /**
         * @return 百度的api接口
         */
        @Singleton
        @Provides
        protected BaiDuApiService provideBaiDuApiService ( ) {
                return getApiService ( UrlUtil.API_BAI_DU ).create ( BaiDuApiService.class );
        }
}
