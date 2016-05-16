package com.github.cchao.touchnews.presenter;

import com.android.volley.VolleyError;
import com.apkfuns.logutils.LogUtils;
import com.github.cchao.touchnews.javaBean.joke.JokeTextRoot;
import com.github.cchao.touchnews.model.JokeTextListMode;
import com.github.cchao.touchnews.model.i.IListMode;
import com.github.cchao.touchnews.presenter.i.IContentListPresenter;
import com.github.cchao.touchnews.util.Constant;
import com.github.cchao.touchnews.util.NetRequestUtil;
import com.github.cchao.touchnews.util.NetUtil;
import com.github.cchao.touchnews.view.JokeTextListView;
import com.google.gson.Gson;

import org.json.JSONObject;

import java.util.List;

/**
 * Created by cchao on 2016/4/25.
 * E-mail:   cchao1024@163.com
 * Description: 文本笑话列表 Presenter
 */
public class JokeTextListPresenter implements IContentListPresenter, NetRequestUtil.RequestListener {
        JokeTextListView mJokeImageListView;
        IListMode mListMode;
        Gson mGson;

        public JokeTextListPresenter ( JokeTextListView jokeImageListView, String page ) {
                mGson = new Gson ( );
                mJokeImageListView = jokeImageListView;
                mListMode = new JokeTextListMode ( this, page );
        }

        @Override
        public void getRefreshData ( ) {
                //无网刷新数据>底部显示SnackBar>点击打开设置界面
                //// TODO: 2016/5/9
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
                JokeTextRoot jokeTextRoot = mGson.fromJson ( response.toString ( ), JokeTextRoot.class );
                if ( jokeTextRoot.getShowapi_res_code ( ) == 0 && jokeTextRoot.getShowapi_res_body ( ) != null ) {

                        List< JokeTextRoot.Contentlist > contentlist = jokeTextRoot.getShowapi_res_body ( ).getContentlist ( );
                        //将文本中的</p><p> 替换
                        for( int i=0;i<contentlist.size ();i++){
                                 filterP(contentlist.get ( i ));
                        }
                        mJokeImageListView.hideInfo ( );
                        if ( jokeTextRoot.getShowapi_res_body ( ).getCurrentPage ( ) == 1 ) {
                                mJokeImageListView.refreshData ( contentlist );
                        } else {
                                mJokeImageListView.addMoreListData ( contentlist );
                        }
                }
        }

        private void filterP ( JokeTextRoot.Contentlist contentlist ) {
                String string = contentlist.getText ();
                String regex="</?p>";
                string= string.replaceAll ( regex,"\n" );
                contentlist.setText (string);
        }

        @Override
        public void onError ( VolleyError error ) {

                LogUtils.i ( error );
        }


}
