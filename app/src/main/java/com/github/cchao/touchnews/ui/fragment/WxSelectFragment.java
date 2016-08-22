package com.github.cchao.touchnews.ui.fragment;

import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.apkfuns.logutils.LogUtils;
import com.github.cchao.touchnews.R;
import com.github.cchao.touchnews.adapter.WxArticleRecyclerAdapter;
import com.github.cchao.touchnews.javaBean.WxArticle.ShowapiResBodyBean.PagebeanBean.ContentlistBean;
import com.github.cchao.touchnews.presenter.BasePresenter;
import com.github.cchao.touchnews.presenter.WxSelectPresenter;
import com.github.cchao.touchnews.ui.fragment.base.PresentFragment;
import com.github.cchao.touchnews.util.Constant;
import com.github.cchao.touchnews.view.WxView;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;

/**
 * Created by cchao on 2016/8/18.
 * E-mail:   cchao1024@163.com
 * Description: 微信精选文章
 */
public class WxSelectFragment extends PresentFragment implements WxView {
        @Bind ( R.id.swipe_refresh_wx_select_list )
        SwipeRefreshLayout mSwipeRefreshLayout;
        @Bind ( R.id.recycle_view_wx_select )
        RecyclerView mRecyclerView;
        List< ContentlistBean > mNewsItemList;
        WxArticleRecyclerAdapter mNewsListRecyclerAdapter;

        @Override
        protected int getLayoutId ( ) {
                return R.layout.fragment_wx_select;
        }

        @Override
        protected BasePresenter getPresenter ( ) {
                return new WxSelectPresenter ( );
        }

        @Override
        protected void initialize ( ) {
                super.initialize ( );
                mNewsItemList = new ArrayList<> ( );
                setRecyclerView ( );
                setSwipeRefreshLayout ( );
                mPresenter.refreshData ( );
        }

        private void setSwipeRefreshLayout ( ) {
                mSwipeRefreshLayout.setColorSchemeResources ( R.color.colorPrimary, R.color.colorPrimaryDark );
                mSwipeRefreshLayout.setOnRefreshListener ( new SwipeRefreshLayout.OnRefreshListener ( ) {
                        @Override
                        public void onRefresh ( ) {
                                mPresenter.refreshData ( );
                        }
                } );
        }

        private void setRecyclerView ( ) {
                mRecyclerView.setHasFixedSize ( true );
                mRecyclerView.setLayoutManager ( new LinearLayoutManager ( mContext ) );
                mNewsListRecyclerAdapter = new WxArticleRecyclerAdapter ( mContext, mNewsItemList );
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
                                        LogUtils.d ( getClass ( ).getSimpleName ( ), "info_loading more data" );
                                        mPresenter.loadMoreData ( );
                                }
                        }
                } );
                mRecyclerView.setAdapter ( mNewsListRecyclerAdapter );
        }

        /**
         * 处理刷新的数据
         *
         * @param articleList articleList
         */
        @Override
        public void onRefreshData ( List< ContentlistBean > articleList ) {
                mNewsItemList.clear ( );
                mNewsItemList.addAll ( articleList );
                mNewsListRecyclerAdapter.notifyDataSetChanged ( );
                mSwipeRefreshLayout.setRefreshing ( false );
        }

        /**
         * 处理添加的数据
         *
         * @param articleList add articleList
         */
        @Override
        public void onAddMoreData ( List< ContentlistBean > articleList ) {
                mNewsItemList.addAll ( articleList );
                mNewsListRecyclerAdapter.notifyDataSetChanged ( );
        }

        @Override
        public void showInfo ( Constant.INFO_TYPE INFOType, String infoText ) {

        }

        @Override
        public void hideInfo ( ) {

        }
}
