package com.github.armstrong.touchnews.model;

import com.github.armstrong.touchnews.BaseApplication;
import com.github.armstrong.touchnews.R;
import com.github.armstrong.touchnews.model.i.IFragmentsContainerModel;
import com.github.armstrong.touchnews.presenter.i.IFragmentsContainerPresenter;
import com.github.armstrong.touchnews.ui.fragment.NewsListFragments;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by cchao on 2016/3/31.
 * E-mail:   cchao1024@163.com
 * Description:
 */
public class NewsContainerFragmentModel implements IFragmentsContainerModel {
        IFragmentsContainerPresenter mPresenter;
        List mFragments;
        String[] mTitles;

        public NewsContainerFragmentModel ( IFragmentsContainerPresenter presenter ) {
                mPresenter = presenter;
        }

        @Override
        public List getFragments ( ) {
                mFragments = new ArrayList ( );
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
                return mFragments;
        }

        @Override
        public String[] getTitles ( ) {
                mTitles = BaseApplication.getContext ( ).getResources ( ).getStringArray ( R.array.news_titles );
                return mTitles;
//                //初始化填充到ViewPager中的Fragment集合
//                mFragments = new ArrayList<> ( );
//                for ( int i = 0 ; i < mTitles.length ; i++ ) {
//                        Bundle mBundle = new Bundle ( );
//                        mBundle.putInt ( "flag", i );
//                        Fragment fragment = new NewsListFragments ( );
//                        fragment.setArguments ( mBundle );
//                        mFragments.add ( i, fragment );
//                }
        }
}
