package com.github.cchao.touchnews.model;

import com.github.cchao.touchnews.BaseApplication;
import com.github.cchao.touchnews.R;
import com.github.cchao.touchnews.model.i.IFragmentsContainerModel;
import com.github.cchao.touchnews.presenter.i.IFragmentsContainerPresenter;
import com.github.cchao.touchnews.ui.fragment.JokeImageListFragments;
import com.github.cchao.touchnews.ui.fragment.JokeTextListFragments;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by cchao on 2016/3/31.
 * E-mail:   cchao1024@163.com
 * Description:
 */
public class JokeContainerFragmentModel implements IFragmentsContainerModel {
        IFragmentsContainerPresenter mPresenter;
        List mFragments;
        String[] mTitles;

        public JokeContainerFragmentModel ( IFragmentsContainerPresenter presenter ) {
                mPresenter = presenter;
        }

        @Override
        public List getFragments ( ) {
                mFragments = new ArrayList ( );
                mFragments.add ( new JokeTextListFragments () );
                mFragments.add ( new JokeImageListFragments () );
                return mFragments;
        }

        @Override
        public String[] getTitles ( ) {
                mTitles = BaseApplication.getContext ( ).getResources ( ).getStringArray ( R.array.joke_titles );
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
