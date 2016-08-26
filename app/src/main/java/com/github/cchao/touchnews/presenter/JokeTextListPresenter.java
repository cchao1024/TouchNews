package com.github.cchao.touchnews.presenter;

import com.github.cchao.touchnews.BaseApplication;
import com.github.cchao.touchnews.contants.Keys;
import com.github.cchao.touchnews.contract.JokeTextListContract;
import com.github.cchao.touchnews.javaBean.joke.JokeTextRoot;
import com.github.cchao.touchnews.util.BaiDuApiService;
import com.github.cchao.touchnews.util.Constant;
import com.github.cchao.touchnews.util.NetUtil;

import java.util.List;

import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

/**
 * Created by cchao on 2016/4/25.
 * E-mail:   cchao1024@163.com
 * Description: 文本笑话列表 Presenter
 */
public class JokeTextListPresenter implements JokeTextListContract.Presenter {
        JokeTextListContract.View mView;
        BaiDuApiService mBaiDApiService;
        int mCurrentPage = 1;

        public JokeTextListPresenter ( JokeTextListContract.View view ) {
                mView = view;
                mBaiDApiService = BaseApplication.getAppComponent ( ).getBaiDuApiService ( );
        }

        @Override
        public void getRefreshData ( ) {
                //无网刷新数据>底部显示SnackBar>点击打开设置界面
                if ( ! NetUtil.isConnected ( ) ) {
                        mView.showInfo ( Constant.INFO_TYPE.NO_NET, null );
                } else {
                        mView.showInfo ( Constant.INFO_TYPE.LOADING, null );
                        mBaiDApiService.getJokeText ( Keys.BAI_DU_KEY, "1" )
                                .subscribeOn ( Schedulers.newThread ( ) )
                                .observeOn ( AndroidSchedulers.mainThread ( ) )
                                .subscribe ( new Action1< JokeTextRoot > ( ) {
                                        @Override
                                        public void call ( JokeTextRoot jokeTextRoot ) {
                                                if ( jokeTextRoot.getShowapi_res_code ( ) == 0 && jokeTextRoot.getShowapi_res_body ( ) != null ) {
                                                        List< JokeTextRoot.Contentlist > contentlist = jokeTextRoot.getShowapi_res_body ( ).getContentlist ( );
                                                        //将文本中的</p><p> 替换
                                                        for ( int i = 0 ; i < contentlist.size ( ) ; i++ ) {
                                                                filterP ( contentlist.get ( i ) );
                                                        }
                                                        mView.hideInfo ( );
                                                        if ( jokeTextRoot.getShowapi_res_body ( ).getCurrentPage ( ) == 1 ) {
                                                                mView.onRefreshData ( contentlist );
                                                        } else {
                                                                mView.onReceiveMoreListData ( contentlist );
                                                        }
                                                }
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
                        mBaiDApiService.getJokeText ( Keys.BAI_DU_KEY, ++ mCurrentPage + "" )
                                .subscribeOn ( Schedulers.newThread ( ) )
                                .observeOn ( AndroidSchedulers.mainThread ( ) )
                                .subscribe ( new Action1< JokeTextRoot > ( ) {
                                        @Override
                                        public void call ( JokeTextRoot jokeTextRoot ) {
                                                if ( jokeTextRoot.getShowapi_res_code ( ) == 0 && jokeTextRoot.getShowapi_res_body ( ) != null ) {
                                                        List< JokeTextRoot.Contentlist > contentlist = jokeTextRoot.getShowapi_res_body ( ).getContentlist ( );
                                                        //将文本中的</p><p> 替换
                                                        for ( int i = 0 ; i < contentlist.size ( ) ; i++ ) {
                                                                filterP ( contentlist.get ( i ) );
                                                        }
                                                        mView.hideInfo ( );
                                                        if ( jokeTextRoot.getShowapi_res_body ( ).getCurrentPage ( ) == 1 ) {
                                                                mView.onRefreshData ( contentlist );
                                                        } else {
                                                                mView.onReceiveMoreListData ( contentlist );
                                                        }
                                                }
                                        }
                                } );
                }
        }

        private void filterP ( JokeTextRoot.Contentlist contentlist ) {
                String string = contentlist.getText ( );
                String regex = "</?p>";
                string = string.replaceAll ( regex, "\n" );
                contentlist.setText ( string );
        }

        @Override
        public void onStart ( ) { }
}
