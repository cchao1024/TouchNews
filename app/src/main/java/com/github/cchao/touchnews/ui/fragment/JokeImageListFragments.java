package com.github.cchao.touchnews.ui.fragment;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.apkfuns.logutils.LogUtils;
import com.github.cchao.touchnews.R;
import com.github.cchao.touchnews.ui.adapter.JokeImageListRecyclerAdapter;
import com.github.cchao.touchnews.javaBean.joke.JokeImageRoot;
import com.github.cchao.touchnews.presenter.JokeImageListPresenter;
import com.github.cchao.touchnews.presenter.i.IContentListPresenter;
import com.github.cchao.touchnews.ui.fragment.base.BaseLazyFragment;
import com.github.cchao.touchnews.util.Constant;
import com.github.cchao.touchnews.util.NetUtil;
import com.github.cchao.touchnews.util.SnackBarUtil;
import com.github.cchao.touchnews.view.JokeImageListView;
import com.github.cchao.touchnews.widget.VaryViewWidget;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by cchao on 2016/3/30.
 * E-mail:   cchao1024@163.com
 * Description: 开心一刻- 图片类Fragment
 */
public class JokeImageListFragments extends BaseLazyFragment implements JokeImageListView, SwipeRefreshLayout.OnRefreshListener {
        @Bind ( R.id.swipe_refresh_joke_image_list )
        SwipeRefreshLayout mSwipeRefreshLayout;
        @Bind ( R.id.recycle_view_joke_image )
        RecyclerView mRecyclerView;
        View mRootView;
        List< JokeImageRoot.Contentlist > mContentList;
        JokeImageListRecyclerAdapter mRecyclerAdapter;
        IContentListPresenter mJokeImageListPresenter;
        VaryViewWidget mVaryViewWidget;

        @Override
        public View onCreateView ( LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState ) {
                super.onCreateView ( inflater, container, savedInstanceState );
                mRootView = inflater.inflate ( R.layout.fragment_joke_image, null );
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

                mRecyclerAdapter = new JokeImageListRecyclerAdapter ( mContext, mContentList );
                mRecyclerView.addOnScrollListener (new RecyclerView.OnScrollListener() {
                        private int lastVisibleItem;
                        @Override
                        public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                                super.onScrolled(recyclerView, dx, dy);
                                lastVisibleItem =((LinearLayoutManager)recyclerView.getLayoutManager ()).findLastVisibleItemPosition();
                        }
                        @Override
                        public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                                super.onScrollStateChanged(recyclerView, newState);
                                if (newState == RecyclerView.SCROLL_STATE_IDLE
                                        && lastVisibleItem + 1 == mRecyclerAdapter.getItemCount()) {
                                        //加载更多
                                        LogUtils.d(getClass ().getSimpleName (),"info_loading more data");
                                        mJokeImageListPresenter.getMoreData ();
                                }
                        }
                });
                mRecyclerView.setAdapter ( mRecyclerAdapter );

                mSwipeRefreshLayout.setColorSchemeResources ( R.color.colorPrimary, R.color.colorPrimaryDark );
                mSwipeRefreshLayout.setOnRefreshListener ( this );

        }

        @Override
        public void onFirstUserVisible ( ) {
                super.onFirstUserVisible ( );
                initViews ( );
                mJokeImageListPresenter = new JokeImageListPresenter ( this, "1" );
                mJokeImageListPresenter.getFirstData ( );
        }

        @Override
        public void onRefresh ( ) {
                mJokeImageListPresenter.getRefreshData ( );
        }

        /**
         * 刷新数据
         * @param newsList newsList
         */
        @Override
        public void onRefreshData ( List< JokeImageRoot.Contentlist > newsList ) {
                mContentList.clear ( );
                mContentList.addAll ( newsList );
                mRecyclerAdapter.notifyDataSetChanged ( );
                mSwipeRefreshLayout.setRefreshing ( false );
                if(mContentList.size ()<7){
                        mJokeImageListPresenter.getMoreData ( );
                }
        }

        /**
         *  上拉添加数据
         * @param newsList add newsList
         */
        @Override
        public void onReceiveMoreListData ( List< JokeImageRoot.Contentlist > newsList ) {
                mContentList.addAll ( newsList );
                mRecyclerAdapter.notifyDataSetChanged ( );
                if(mContentList.size ()<7){
                        mJokeImageListPresenter.getMoreData ( );
                }
        }

        /**
         * @param INFOType     枚举值 e.g. 没有网络、正在加载、异常
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
                                infoView = LayoutInflater.from ( mContext ).inflate ( R.layout.info_loading, null );
                                break;
                        case NO_NET:
                                //已有数据（那就是刷新或者加载更多）-> 那么在底部snack检测网络设置
                                if ( mContentList.size ( ) > 1 ) {
                                        SnackBarUtil.showLong ( mRootView, R.string.none_net_show_action ).setAction ( R.string.set, new View.OnClickListener ( ) {
                                                @Override
                                                public void onClick ( View v ) {
                                                        NetUtil.openSetting ( getActivity ( ) );
                                                        mSwipeRefreshLayout.setRefreshing ( false );
                                                }
                                        } );
                                } else {
                                        //没有数据（那就是第一次加载数据）->点击重试>从新加载第一次资源
                                        infoView = LayoutInflater.from ( mContext ).inflate ( R.layout.info_no_net, null );
                                        infoView.findViewById ( R.id.tv_try_again ).setOnClickListener ( new View.OnClickListener ( ) {
                                                @Override
                                                public void onClick ( View v ) {
                                                        mJokeImageListPresenter.getFirstData ( );
                                                }
                                        } );
                                        mVaryViewWidget.setNoNetView ( infoView );
                                }
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
