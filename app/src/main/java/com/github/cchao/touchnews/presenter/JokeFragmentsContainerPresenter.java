package com.github.cchao.touchnews.presenter;

import com.github.cchao.touchnews.model.JokeContainerFragmentModel;
import com.github.cchao.touchnews.model.i.IFragmentsContainerModel;
import com.github.cchao.touchnews.presenter.i.IFragmentsContainerPresenter;
import com.github.cchao.touchnews.view.FragmentsContainerView;

/**
 * Created by cchao on 2016/3/31.
 * E-mail:   cchao1024@163.com
 * Description:
 */
public class JokeFragmentsContainerPresenter implements IFragmentsContainerPresenter {
        FragmentsContainerView mView;
        IFragmentsContainerModel mModel;

        public JokeFragmentsContainerPresenter ( FragmentsContainerView view ) {
                mView = view;
                mModel = new JokeContainerFragmentModel ( this );
        }

        @Override
        public void getFragments ( ) {
                mView.onSetFragment ( mModel.getFragments ( ), mModel.getTitles ( ) );
        }
}
