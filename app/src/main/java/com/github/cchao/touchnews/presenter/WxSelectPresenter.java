package com.github.cchao.touchnews.presenter;


import com.github.cchao.touchnews.contants.Keys;
import com.github.cchao.touchnews.javaBean.WxArticle;
import com.github.cchao.touchnews.ui.fragment.WxView;

import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

/**
 * Created by cchao on 2016/8/18.
 * E-mail:   cchao1024@163.com
 * Description:
 */
public class WxSelectPresenter extends ListDataPresenter< WxView > {

        int mPage = 1; //页码

        @Override
        public void refreshData ( ) {
                super.refreshData ( );
                mPage = 1;
                mBaiDApiService.getWxArticle ( Keys.BAI_DU_KEY, "1" )
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
                mBaiDApiService.getWxArticle ( Keys.BAI_DU_KEY, String.valueOf ( ++ mPage ) )
                        .subscribeOn ( Schedulers.newThread ( ) )
                        .observeOn ( AndroidSchedulers.mainThread ( ) )
                        .subscribe ( new Action1< WxArticle > ( ) {
                                @Override
                                public void call ( WxArticle wxArticle ) {
                                        mView.onAddMoreData ( wxArticle.getShowapi_res_body ( ).getPagebean ( ).getContentlist ( ) );
                                }
                        } );
        }

}
