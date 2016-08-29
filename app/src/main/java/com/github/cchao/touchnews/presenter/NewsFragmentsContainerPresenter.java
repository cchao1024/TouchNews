package com.github.cchao.touchnews.presenter;

import com.github.cchao.touchnews.BaseApplication;
import com.github.cchao.touchnews.R;
import com.github.cchao.touchnews.contract.FragmentContainerContract;
import com.github.cchao.touchnews.ui.fragment.NewsListFragments;
import com.github.cchao.touchnews.ui.fragment.base.BaseFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by cchao on 2016/3/31.
 * E-mail:   cchao1024@163.com
 * Description:
 */
public class NewsFragmentsContainerPresenter implements FragmentContainerContract.Presenter {
        List< BaseFragment > mFragments;
        String[] mTitles;
        FragmentContainerContract.View mView;

        public NewsFragmentsContainerPresenter ( FragmentContainerContract.View view ) {
                mView = view;
                mView.setFragment ( getFragments ( ), getTitles ( ) );
        }

        /**
         * @return 新闻频道List
         */
        public List< BaseFragment > getFragments ( ) {
                if ( mFragments == null ) {
                        mFragments = new ArrayList<> ( );
                        //Arguments 加入新闻频道ID
                        String[] newsChannelId = BaseApplication.getContext ( ).getResources ( ).getStringArray ( R.array.news_channelId );
                        for ( int i = 0 ; i < newsChannelId.length ; i++ ) {
                                NewsListFragments newsListFragments = new NewsListFragments ( );
//                        Bundle bundle= new Bundle (  );
//                        bundle.putString ( "channelId", newsChannelId[ i ] );
//                        newsListFragments.setArguments ( bundle );
                                newsListFragments.setChannelId ( newsChannelId[ i ] );
                                mFragments.add ( newsListFragments );
                        }
                }
                return mFragments;
        }

        /**
         * @return 新闻页的标题
         */
        public String[] getTitles ( ) {
                mTitles = BaseApplication.getContext ( ).getResources ( ).getStringArray ( R.array.news_titles );
                return mTitles;
        }


}
