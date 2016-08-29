package com.github.cchao.touchnews.ui.fragment;

import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;

import com.github.cchao.touchnews.R;
import com.github.cchao.touchnews.contract.NewListDataContract;
import com.github.cchao.touchnews.javaBean.news.Contentlist;
import com.github.cchao.touchnews.presenter.NewsListPresenter;
import com.github.cchao.touchnews.ui.adapter.NewsListRecyclerAdapter;
import com.github.cchao.touchnews.ui.fragment.base.BaseFragment;
import com.github.cchao.touchnews.util.Constant;
import com.github.cchao.touchnews.util.NetUtil;
import com.github.cchao.touchnews.util.SnackBarUtil;
import com.github.cchao.touchnews.widget.VaryViewWidget;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;

/**
 * Created by cchao on 2016/3/30.
 * E-mail:   cchao1024@163.com
 * Description: 新闻资讯List Fragment
 */
public class NewsListFragments extends BaseFragment implements NewListDataContract.View, SwipeRefreshLayout.OnRefreshListener {
        @Bind ( R.id.swipe_refresh_news_list )
        SwipeRefreshLayout mSwipeRefreshLayout;
        @Bind ( R.id.recycle_view_news )
        RecyclerView mRecyclerView;
        View mRootView;
        List< Contentlist > mNewsItemList;
        NewsListRecyclerAdapter mNewsListRecyclerAdapter;
        NewListDataContract.Presenter mNewsListPresenter;
        VaryViewWidget mVaryViewWidget;
        //新闻频道的ID
        private String mChannelId = null;

        @Override
        protected int getLayoutId ( ) {
                return R.layout.fragment_news_list;
        }

        @Override
        protected void initialize ( ) {
                super.initialize ( );
                mNewsListPresenter = new NewsListPresenter ( this, mChannelId );
        }

        private void initViews ( ) {

                mRecyclerView.setHasFixedSize ( true );
                mRecyclerView.setLayoutManager ( new LinearLayoutManager ( mContext ) );
                mNewsListRecyclerAdapter = new NewsListRecyclerAdapter ( mContext, mNewsItemList );
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
                                        && lastVisibleItem + 1 == mNewsListRecyclerAdapter.getItemCount ( ) ) {
                                        //加载更多
                                        mNewsListPresenter.getMoreData ( );
                                }
                        }
                } );
                mRecyclerView.setAdapter ( mNewsListRecyclerAdapter );
                mSwipeRefreshLayout.setColorSchemeResources ( R.color.colorPrimary, R.color.colorPrimaryDark );
                mSwipeRefreshLayout.setOnRefreshListener ( this );
        }

        /**
         * 设置新闻频道的唯一ID值
         *
         * @param channelId id值  @link：http://apistore.baidu.com/apiworks/servicedetail/688.html
         */
        public void setChannelId ( String channelId ) {
                mChannelId = channelId;
        }

        @Override
        public void onFirstUserVisible ( ) {
                super.onFirstUserVisible ( );
                mNewsItemList = new ArrayList<> ( );
                initViews ( );
                mNewsListPresenter.getRefreshData ( );
        }
        @Override
        public void onRefresh ( ) {
                mNewsListPresenter.getRefreshData ( );
        }

        /**
         * 刷新数据
         *
         * @param newsList newsList
         */
        @Override
        public void onRefreshData ( List< Contentlist > newsList ) {
                mNewsItemList.clear ( );
                mNewsItemList.addAll ( newsList );
                mNewsListRecyclerAdapter.notifyDataSetChanged ( );
                mSwipeRefreshLayout.setRefreshing ( false );
        }

        /**
         * 上拉 添加数据
         *
         * @param newsList add newsList
         */
        @Override
        public void onReceiveMoreListData ( List< Contentlist > newsList ) {
                mNewsItemList.addAll ( newsList );
                mNewsListRecyclerAdapter.notifyDataSetChanged ( );
                if ( mNewsItemList.size ( ) < 7 ) {
                        mNewsListPresenter.getMoreData ( );
                }
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
                View infoView;
                switch ( INFOType ) {
                        case LOADING:
                                infoView = LayoutInflater.from ( mContext ).inflate ( R.layout.info_loading, null );
                                mVaryViewWidget.setLoadingView ( infoView );
                                break;
                        case NO_NET:
                                //已有数据（那就是刷新或者加载更多）-> 那么在底部snack检测网络设置
                                if ( mNewsItemList.size ( ) > 1 ) {
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
                                                        mNewsListPresenter.getRefreshData ( );
                                                }
                                        } );
                                        mVaryViewWidget.setNoNetView ( infoView );
                                }
                                break;
                }
                mVaryViewWidget.showView ( INFOType );
        }

        @Override
        public void hideInfo ( ) {
                if ( mVaryViewWidget != null ) {
                        mVaryViewWidget.hideInfo ( );
                }
        }
}
