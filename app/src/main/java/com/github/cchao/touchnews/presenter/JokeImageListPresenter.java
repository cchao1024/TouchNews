package com.github.cchao.touchnews.presenter;

import android.util.Log;

import com.github.cchao.touchnews.BaseApplication;
import com.github.cchao.touchnews.contract.JokeImageListContract;
import com.github.cchao.touchnews.javaBean.joke.JokeImageRoot;
import com.github.cchao.touchnews.util.BaiDuApiService;
import com.github.cchao.touchnews.util.Constant;
import com.github.cchao.touchnews.util.Keys;
import com.github.cchao.touchnews.util.LogUtil;
import com.github.cchao.touchnews.util.NetUtil;

import java.util.List;

import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

/**
 * Created by cchao on 2016/4/25.
 * E-mail:   cchao1024@163.com
 * Description: 图片笑话列表 Presenter
 */
public class JokeImageListPresenter implements JokeImageListContract.Presenter {
        JokeImageListContract.View mView;
        BaiDuApiService mBaiDBaiDuApiService;
        int mCurrentPage = 1;

        public JokeImageListPresenter ( JokeImageListContract.View view ) {
                mView = view;
                mBaiDBaiDuApiService = BaseApplication.getAppComponent ( ).getBaiDuApiService ( );
        }

        @Override
        public void getRefreshData ( ) {
                //无网刷新数据>底部显示SnackBar>点击打开设置界面
                if ( ! NetUtil.isConnected ( ) ) {
                        mView.showInfo ( Constant.INFO_TYPE.NO_NET, null );
                } else {
                        mView.showInfo ( Constant.INFO_TYPE.LOADING, null );
                        mBaiDBaiDuApiService.getJokeImage ( Keys.BAI_DU_KEY, "1" )
                                .subscribeOn ( Schedulers.newThread ( ) )
                                .observeOn ( AndroidSchedulers.mainThread ( ) )
                                .subscribe ( new Action1< JokeImageRoot > ( ) {
                                        @Override
                                        public void call ( JokeImageRoot jokeimageRoot ) {
                                                if ( jokeimageRoot.getShowapi_res_code ( ) == 0 && jokeimageRoot.getShowapi_res_body ( ) != null ) {

                                                        List< JokeImageRoot.Contentlist > contentlist = jokeimageRoot.getShowapi_res_body ( ).getContentlist ( );
                                                        mView.hideInfo ( );
                                                        if ( jokeimageRoot.getShowapi_res_body ( ).getCurrentPage ( ) == 1 ) {
                                                                mView.onRefreshData ( contentlist );
                                                        } else {
                                                                mView.onReceiveMoreListData ( contentlist );
                                                        }
                                                }
                                        }
                                } , new Action1< Throwable > ( ) {
                                        @Override
                                        public void call ( Throwable throwable ) {
                                                LogUtil.e ( throwable.toString () );
                                        }
                                } );
                }
        }

        @Override
        public void getMoreData ( ) {
                //无网刷新数据>底部显示SnackBar>点击打开设置界面
                if ( ! NetUtil.isConnected ( ) ) {
                        mView.showInfo ( Constant.INFO_TYPE.NO_NET, null );
                } else {
                        mView.showInfo ( Constant.INFO_TYPE.LOADING, null );
                        mBaiDBaiDuApiService.getJokeImage ( Keys.BAI_DU_KEY, ++ mCurrentPage + "" )
                                .subscribeOn ( Schedulers.newThread ( ) )
                                .observeOn ( AndroidSchedulers.mainThread ( ) )
                                .subscribe ( new Action1< JokeImageRoot > ( ) {
                                        @Override
                                        public void call ( JokeImageRoot jokeimageRoot ) {
                                                if ( jokeimageRoot.getShowapi_res_code ( ) == 0 && jokeimageRoot.getShowapi_res_body ( ) != null ) {

                                                        List< JokeImageRoot.Contentlist > contentlist = jokeimageRoot.getShowapi_res_body ( ).getContentlist ( );
                                                        mView.hideInfo ( );
                                                        if ( jokeimageRoot.getShowapi_res_body ( ).getCurrentPage ( ) == 1 ) {
                                                                mView.onRefreshData ( contentlist );
                                                        } else {
                                                                mView.onReceiveMoreListData ( contentlist );
                                                        }
                                                }
                                        }
                                }, new Action1< Throwable > ( ) {
                                        @Override
                                        public void call ( Throwable throwable ) {
                                                LogUtil.e ( throwable.toString () );
                                        }
                                }  );
                }
        }

}
