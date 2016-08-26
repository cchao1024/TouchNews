package com.github.cchao.touchnews.presenter;

import com.android.volley.VolleyError;
import com.apkfuns.logutils.LogUtils;
import com.github.cchao.touchnews.contract.NewListDataContract;
import com.github.cchao.touchnews.javaBean.news.Contentlist;
import com.github.cchao.touchnews.javaBean.news.NewsItemRoot;
import com.github.cchao.touchnews.javaBean.news.Pagebean;
import com.github.cchao.touchnews.util.Constant;
import com.github.cchao.touchnews.util.NetRequestUtil;
import com.github.cchao.touchnews.util.NetUtil;
import com.github.cchao.touchnews.util.UrlUtil;
import com.google.gson.Gson;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;

/**
 * Created by cchao on 2016/4/5.
 * E-mail:   cchao1024@163.com
 * Description:
 */
public class NewsListPresenter implements NewListDataContract.Presenter, NetRequestUtil.RequestListener {
        NewListDataContract.View mView;
        Gson mGson;
        int mCurrentPage = 1;
        Map< String, String > param = new HashMap<> ( );
        Map< String, String > headers = new HashMap<> ( );
        String channelId;

        public NewsListPresenter ( NewListDataContract.View view, String channelId ) {
                mGson = new Gson ( );
                mView = view;
                this.channelId = channelId;
                headers.put ( "apikey", "1bb91df70ccde8148a2c3da582ca9ff2" );
                param.put ( "channelId", channelId );
        }

        @Override
        public void onStart ( ) {}

        public void loadRefreshData ( ) {
                mCurrentPage = 1;
                loadMoreData ( );
        }

        public void loadMoreData ( ) {
                param.put ( "page", "" + mCurrentPage++ );
                NetRequestUtil.getInstance ( ).getJsonWithHeaders ( UrlUtil.URL_NEWS, param, headers, this );
        }


        @Override
        public void getRefreshData ( ) {
                //无网刷新数据>底部显示SnackBar>点击打开设置界面
                if ( ! NetUtil.isConnected ( ) ) {
                        mView.showInfo ( Constant.INFO_TYPE.NO_NET, null );
                } else {
                        mView.showInfo ( Constant.INFO_TYPE.LOADING, null );
                        loadRefreshData ( );
                }
        }

        @Override
        public void getFirstData ( ) {
                if ( ! NetUtil.isConnected ( ) ) {
                        mView.showInfo ( Constant.INFO_TYPE.NO_NET, null );
                } else {
                        mView.showInfo ( Constant.INFO_TYPE.LOADING, null );
                        loadRefreshData ( );
                }
        }

        @Override
        public void getMoreData ( ) {
                loadMoreData ( );
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
                        mView.hideInfo ( );
                        if ( pagebean.getCurrentPage ( ) == 1 ) {
                                mView.onRefreshData ( contentlist );
                        }else{
                                mView.onReceiveMoreListData ( contentlist );
                        }
                }
        }

        @Override
        public void onError ( VolleyError error ) {

                LogUtils.i ( error );
        }


}
