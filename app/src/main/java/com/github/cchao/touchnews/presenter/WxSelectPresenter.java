package com.github.cchao.touchnews.presenter;


import com.github.cchao.touchnews.javaBean.WxArticle;
import com.github.cchao.touchnews.manager.ApiServiceManager;
import com.github.cchao.touchnews.util.ApiService;
import com.github.cchao.touchnews.util.ToastUtil;
import com.github.cchao.touchnews.view.WxView;

import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by cchao on 2016/8/18.
 * E-mail:   cchao1024@163.com
 * Description:
 */
public class WxSelectPresenter extends BasePresenter< WxView > {

        ApiService mApiService;
        int mPage = 1; //页码

        public WxSelectPresenter ( ) {
                mApiService = ApiServiceManager.getApiService ( "http://apis.baidu.com/" );
        }

        @Override
        public void refreshData ( ) {
                super.refreshData ( );
                mPage = 1;
                mApiService.getID ( "96a573502b09d771c6431c106d04613a", "1" )
                        .subscribeOn ( Schedulers.newThread ( ) )
                        .observeOn ( AndroidSchedulers.mainThread ( ) )
                        .subscribe ( new Observer< WxArticle > ( ) {
                                @Override
                                public void onCompleted ( ) {}

                                @Override
                                public void onError ( Throwable e ) {
                                        ToastUtil.showShortToast ( e.toString ( ) );
                                }

                                @Override
                                public void onNext ( WxArticle wxArticle ) {
                                        mView.onRefreshData ( wxArticle.getShowapi_res_body ( ).getPagebean ( ).getContentlist ( ) );
                                }
                        } );
        }

        @Override
        public void loadMoreData ( ) {
                super.loadMoreData ( );
                mApiService.getID ( "96a573502b09d771c6431c106d04613a", String.valueOf ( ++ mPage ) )
                        .subscribeOn ( Schedulers.newThread ( ) )
                        .observeOn ( AndroidSchedulers.mainThread ( ) )
                        .subscribe ( new Observer< WxArticle > ( ) {
                                @Override
                                public void onCompleted ( ) {}

                                @Override
                                public void onError ( Throwable e ) { ToastUtil.showShortToast ( e.toString ( ) );}

                                @Override
                                public void onNext ( WxArticle wxArticle ) {
                                        mView.onAddMoreData ( wxArticle.getShowapi_res_body ( ).getPagebean ( ).getContentlist ( ) );
                                }
                        } );
        }
}
