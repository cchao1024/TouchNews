package com.github.cchao.touchnews.presenter;

import com.android.volley.VolleyError;
import com.apkfuns.logutils.LogUtils;
import com.github.cchao.touchnews.javaBean.news.Contentlist;
import com.github.cchao.touchnews.javaBean.news.NewsItemRoot;
import com.github.cchao.touchnews.javaBean.news.Pagebean;
import com.github.cchao.touchnews.model.NewsListMode;
import com.github.cchao.touchnews.presenter.i.IContentListPresenter;
import com.github.cchao.touchnews.util.Constant;
import com.github.cchao.touchnews.util.NetRequestUtil;
import com.github.cchao.touchnews.util.NetUtil;
import com.github.cchao.touchnews.view.NewsListView;
import com.google.gson.Gson;

import org.json.JSONObject;

import java.util.List;
import java.util.ListIterator;

/**
 * Created by cchao on 2016/4/5.
 * E-mail:   cchao1024@163.com
 * Description:
 */
public class NewsListPresenter implements IContentListPresenter, NetRequestUtil.RequestListener {
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
                        mNewsListView.showInfo ( Constant.INFO_TYPE.NO_NET, null );
                } else {
                        mNewsListView.showInfo ( Constant.INFO_TYPE.LOADING,null);
                        mNewsListMode.loadRefreshData ( );
                }
        }

        @Override
        public void getFirstData ( ) {
                if ( ! NetUtil.isConnected ( ) ) {
                        mNewsListView.showInfo ( Constant.INFO_TYPE.NO_NET, null );
                } else {
                        mNewsListView.showInfo ( Constant.INFO_TYPE.LOADING,null );
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
                NewsItemRoot newsItemRoot = mGson.fromJson ( response.toString ( ), NewsItemRoot.class );
                if ( newsItemRoot.getShowapi_res_code ( ) == 0 && newsItemRoot.getShowapi_res_body ( ) != null ) {
                        Pagebean pagebean = newsItemRoot.getShowapi_res_body ( ).getPagebean ( );
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
                        }else{
                                mNewsListView.addMoreListData ( contentlist );
                        }
                }
        }

        @Override
        public void onError ( VolleyError error ) {

                LogUtils.i ( error );
        }
}
