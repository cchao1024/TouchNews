package com.github.armstrong.touchnews.ui.fragment;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.apkfuns.logutils.LogUtils;
import com.github.armstrong.touchnews.R;
import com.github.armstrong.touchnews.adapter.JokeImageListRecyclerAdapter;
import com.github.armstrong.touchnews.adapter.JokeTextListRecyclerAdapter;
import com.github.armstrong.touchnews.javaBean.joke.JokeImageRoot;
import com.github.armstrong.touchnews.javaBean.joke.JokeTextRoot;
import com.github.armstrong.touchnews.presenter.JokeImageListPresenter;
import com.github.armstrong.touchnews.presenter.JokeTextListPresenter;
import com.github.armstrong.touchnews.presenter.i.IContentListPresenter;
import com.github.armstrong.touchnews.ui.fragment.base.BaseLazyFragment;
import com.github.armstrong.touchnews.util.Constant;
import com.github.armstrong.touchnews.view.JokeImageListView;
import com.github.armstrong.touchnews.view.JokeTextListView;
import com.github.armstrong.touchnews.widget.VaryViewWidget;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by cchao on 2016/3/30.
 * E-mail:   cchao1024@163.com
 * Description:
 */
public class JokeTextListFragments extends BaseLazyFragment implements JokeTextListView, SwipeRefreshLayout.OnRefreshListener {

        @Bind ( R.id.swipe_refresh_joke_text_list )
        SwipeRefreshLayout mSwipeRefreshLayout;
        @Bind ( R.id.recycle_view_joke_text )
        RecyclerView mRecyclerView;
        View mRootView;
        List< JokeTextRoot.Contentlist > mContentList;
        JokeTextListRecyclerAdapter mRecyclerAdapter;
        IContentListPresenter mJokeImageListPresenter;
        VaryViewWidget mVaryViewWidget;

        @Override
        public View onCreateView ( LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState ) {
                super.onCreateView ( inflater, container, savedInstanceState );
                mRootView = inflater.inflate ( R.layout.fragment_joke_text, null );
                ButterKnife.bind ( this, mRootView );
                return mRootView;
        }

        @Override
        public void onViewCreated ( View view, Bundle savedInstanceState ) {
                super.onViewCreated ( view, savedInstanceState );
        }

        private void initViews ( ) {
                mContentList = new ArrayList<> ( );
                mRecyclerView.setHasFixedSize ( true );
                mRecyclerView.setLayoutManager ( new LinearLayoutManager ( mContext ) );

                mRecyclerAdapter = new JokeTextListRecyclerAdapter ( mContext, mContentList );
                mRecyclerView.addOnScrollListener ( new RecyclerView.OnScrollListener ( ) {
                        private int lastVisibleItem;

                        @Override
                        public void onScrolled ( RecyclerView recyclerView, int dx, int dy ) {
                                super.onScrolled ( recyclerView, dx, dy );
                                lastVisibleItem = ( ( LinearLayoutManager ) recyclerView.getLayoutManager ( ) ).findLastVisibleItemPosition ( );
                        }

                        @Override
                        public void onScrollStateChanged ( RecyclerView recyclerView, int newState ) {
                                super.onScrollStateChanged ( recyclerView, newState );
                                if ( newState == RecyclerView.SCROLL_STATE_IDLE
                                        && lastVisibleItem + 1 == mRecyclerAdapter.getItemCount ( ) ) {
                                        //加载更多
                                        LogUtils.d ( getClass ( ).getSimpleName ( ), "loading more data" );
                                        mJokeImageListPresenter.getMoreData ( );
                                }
                        }
                } );
                mRecyclerView.setAdapter ( mRecyclerAdapter );

                mSwipeRefreshLayout.setColorSchemeResources ( R.color.colorPrimary, R.color.colorPrimaryDark );
                mSwipeRefreshLayout.setOnRefreshListener ( this );

        }

        @Override
        public void onFirstUserVisible ( ) {
                super.onFirstUserVisible ( );
                initViews ( );
                mJokeImageListPresenter = new JokeTextListPresenter ( this, "1" );
                mJokeImageListPresenter.getFirstData ( );
        }

        @Override
        public void onRefresh ( ) {
                mJokeImageListPresenter.getRefreshData ( );
        }

        @Override
        public void refreshData ( List< JokeTextRoot.Contentlist > newsList ) {
                mContentList.clear ( );
                mContentList.addAll ( newsList );
                mRecyclerAdapter.notifyDataSetChanged ( );
                mSwipeRefreshLayout.setRefreshing ( false );
        }

        @Override
        public void addMoreListData ( List< JokeTextRoot.Contentlist > newsList ) {
                mContentList.addAll ( newsList );
                mRecyclerAdapter.notifyDataSetChanged ( );
        }

        /**
         * @param INFOType 枚举值 e.g. 没有网络、正在加载、异常
         * @param infoText infoText 提示的文本内容
         */
        @Override
        public void showInfo ( Constant.INFO_TYPE INFOType, String infoText ) {
                if ( mVaryViewWidget == null ) {
                        mVaryViewWidget = new VaryViewWidget ( mSwipeRefreshLayout );
                }
                View infoView = null;
                switch ( INFOType ) {
                        case LOADING:
                                infoView = LayoutInflater.from ( mContext ).inflate ( R.layout.loading, null );
                                break;
                }
                mVaryViewWidget.setLoadingView ( infoView );
                mVaryViewWidget.showView ( INFOType );
        }

        @Override
        public void hideInfo ( ) {
                if ( mVaryViewWidget != null ) {
                        mVaryViewWidget.hideInfo ( );
                }


        }

}
