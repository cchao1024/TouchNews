package com.github.armstrong.touchnews.presenter;

import com.android.volley.VolleyError;
import com.apkfuns.logutils.LogUtils;
import com.github.armstrong.touchnews.javaBean.news.Contentlist;
import com.github.armstrong.touchnews.javaBean.news.NewsEntity;
import com.github.armstrong.touchnews.javaBean.news.Pagebean;
import com.github.armstrong.touchnews.model.NewsListMode;
import com.github.armstrong.touchnews.presenter.i.INewsListPresenter;
import com.github.armstrong.touchnews.util.Constant;
import com.github.armstrong.touchnews.util.NetRequestUtil;
import com.github.armstrong.touchnews.util.NetUtil;
import com.github.armstrong.touchnews.view.NewsListView;
import com.google.gson.Gson;

import org.json.JSONObject;

import java.util.List;
import java.util.ListIterator;

/**
 * Created by cchao on 2016/4/5.
 * E-mail:   cchao1024@163.com
 * Description:
 */
public class NewsListPresenter implements INewsListPresenter, NetRequestUtil.RequestListener {
        NewsListView mNewsListView;
        NewsListMode mNewsListMode;
        Gson mGson;

        public NewsListPresenter ( NewsListView newsListView, String channelId ) {
                mGson = new Gson ( );
                mNewsListView = newsListView;
                mNewsListMode = new NewsListMode ( this, channelId );
        }

        @Override
        public void getRefreshData ( ) {
                //无网刷新数据>底部显示SnackBar>点击打开设置界面
                if ( ! NetUtil.isConnected ( ) ) {
                        mNewsListView.showInfo ( Constant.TYPE.NO_NET, null );
                } else {
                        mNewsListView.showInfo ( Constant.TYPE.LOADING,null);
                        mNewsListMode.loadRefreshData ( );
                }
        }

        @Override
        public void getFirstData ( ) {
                if ( ! NetUtil.isConnected ( ) ) {
                        mNewsListView.showInfo ( Constant.TYPE.NO_NET, null );
                } else {
                        mNewsListView.showInfo ( Constant.TYPE.LOADING,null );
                        mNewsListMode.loadRefreshData ( );
                }
        }

        @Override
        public void getMoreData ( ) {
                mNewsListMode.loadMoreData ( );
        }

        @Override
        public void onResponse ( JSONObject response ) {
                LogUtils.i ( response );
                NewsEntity newsEntity = mGson.fromJson ( response.toString ( ), NewsEntity.class );
                if ( newsEntity.getShowapi_res_code ( ) == 0 && newsEntity.getShowapi_res_body ( ) != null ) {
                        Pagebean pagebean = newsEntity.getShowapi_res_body ( ).getPagebean ( );
                        List< Contentlist > contentlist = pagebean.getContentlist ( );
                        //没图你说个球呀
                        ListIterator< Contentlist > iterator = contentlist.listIterator ( );
                        while ( iterator.hasNext ( ) ) {
                                //把没图的remove掉
                                if ( iterator.next ( ).getImageurls ( ).size ( ) <= 0 ) {
                                        iterator.remove ( );
                                }
                        }
                        mNewsListView.hideInfo (  );
                        if ( pagebean.getCurrentPage ( ) == 1 ) {
                                mNewsListView.refreshData ( contentlist );
                        }
                        mNewsListView.addMoreListData ( contentlist );
                }
        }

        @Override
        public void onError ( VolleyError error ) {
                LogUtils.i ( error );
        }
}
