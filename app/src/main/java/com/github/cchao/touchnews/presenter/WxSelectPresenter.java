package com.github.cchao.touchnews.presenter;


import com.github.cchao.touchnews.javaBean.WxArticle;
import com.github.cchao.touchnews.manager.ApiServiceManager;
import com.github.cchao.touchnews.util.BaiDuApiService;
import com.github.cchao.touchnews.view.WxView;

import javax.inject.Inject;

import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

/**
 * Created by cchao on 2016/8/18.
 * E-mail:   cchao1024@163.com
 * Description:
 */
public class WxSelectPresenter extends BasePresenter< WxView > {

        BaiDuApiService mBaiDuApiService;
        int mPage = 1; //页码

        @Inject
        public WxSelectPresenter ( ) {
                mBaiDuApiService = ApiServiceManager.getApiService ( "http://apis.baidu.com/" );
        }

        @Override
        public void refreshData ( ) {
                super.refreshData ( );
                mPage = 1;
                mBaiDuApiService.getWxArticle ( "96a573502b09d771c6431c106d04613a", "1" )
                        .subscribeOn ( Schedulers.newThread ( ) )
                        .observeOn ( AndroidSchedulers.mainThread ( ) )
                        .subscribe ( new Action1< WxArticle > ( ) {
                                @Override
                                public void call ( WxArticle wxArticle ) {
                                        mView.onRefreshData ( wxArticle.getShowapi_res_body ( ).getPagebean ( ).getContentlist ( ) );
                                }
                        } );
        }

        @Override
        public void loadMoreData ( ) {
                super.loadMoreData ( );
                mBaiDuApiService.getWxArticle ( "96a573502b09d771c6431c106d04613a", String.valueOf ( ++ mPage ) )
                        .subscribeOn ( Schedulers.newThread ( ) )
                        .observeOn ( AndroidSchedulers.mainThread ( ) )
                        .subscribe (  new Action1< WxArticle > ( ) {
                                @Override
                                public void call ( WxArticle wxArticle ) {
                                        mView.onAddMoreData ( wxArticle.getShowapi_res_body ( ).getPagebean ( ).getContentlist ( ) );
                                }
                        } );
        }
}
