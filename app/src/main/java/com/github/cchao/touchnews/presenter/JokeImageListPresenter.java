package com.github.cchao.touchnews.presenter;

import com.android.volley.VolleyError;
import com.apkfuns.logutils.LogUtils;
import com.github.cchao.touchnews.javaBean.joke.JokeImageRoot;
import com.github.cchao.touchnews.model.JokeImageListMode;
import com.github.cchao.touchnews.model.i.IListMode;
import com.github.cchao.touchnews.presenter.i.IContentListPresenter;
import com.github.cchao.touchnews.util.Constant;
import com.github.cchao.touchnews.util.NetRequestUtil;
import com.github.cchao.touchnews.util.NetUtil;
import com.github.cchao.touchnews.view.JokeImageListView;
import com.google.gson.Gson;

import org.json.JSONObject;

import java.util.List;

/**
 * Created by cchao on 2016/4/25.
 * E-mail:   cchao1024@163.com
 * Description: 图片笑话列表 Presenter
 */
public class JokeImageListPresenter implements IContentListPresenter, NetRequestUtil.RequestListener {
        JokeImageListView mJokeImageListView;
        IListMode mListMode;
        Gson mGson;

        public JokeImageListPresenter ( JokeImageListView jokeImageListView, String page ) {
                mGson = new Gson ( );
                mJokeImageListView = jokeImageListView;
                mListMode = new JokeImageListMode ( this, page );
        }

        @Override
        public void getRefreshData ( ) {
                //无网刷新数据>底部显示SnackBar>点击打开设置界面
                if ( ! NetUtil.isConnected ( ) ) {
                        mJokeImageListView.showInfo ( Constant.INFO_TYPE.NO_NET, null );
                } else {
                        mJokeImageListView.showInfo ( Constant.INFO_TYPE.LOADING, null );
                        mListMode.loadRefreshData ( );
                }
        }

        @Override
        public void getFirstData ( ) {
                if ( ! NetUtil.isConnected ( ) ) {
                        mJokeImageListView.showInfo ( Constant.INFO_TYPE.NO_NET, null );
                } else {
                        mJokeImageListView.showInfo ( Constant.INFO_TYPE.LOADING, null );
                        mListMode.loadRefreshData ( );
                }
        }

        @Override
        public void getMoreData ( ) {
                mListMode.loadMoreData ( );
        }

        @Override
        public void onResponse ( JSONObject response ) {
                LogUtils.i ( response );
                JokeImageRoot jokeImageRoot = mGson.fromJson ( response.toString ( ), JokeImageRoot.class );
                if ( jokeImageRoot.getShowapi_res_code ( ) == 0 && jokeImageRoot.getShowapi_res_body ( ) != null ) {

                        List< JokeImageRoot.Contentlist > contentlist = jokeImageRoot.getShowapi_res_body ( ).getContentlist ( );
                        mJokeImageListView.hideInfo ( );
                        if ( jokeImageRoot.getShowapi_res_body ( ).getCurrentPage ( ) == 1 ) {
                                mJokeImageListView.onRefreshData ( contentlist );
                        } else {
                                mJokeImageListView.onReceiveMoreListData ( contentlist );
                        }
                }
        }

        @Override
        public void onError ( VolleyError error ) {

                LogUtils.i ( error );
        }


}
