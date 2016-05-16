package com.github.cchao.touchnews.presenter;

import com.github.cchao.touchnews.model.i.IFragmentsContainerModel;
import com.github.cchao.touchnews.model.NewsContainerFragmentModel;
import com.github.cchao.touchnews.presenter.i.IFragmentsContainerPresenter;
import com.github.cchao.touchnews.view.FragmentsContainerView;

/**
 * Created by cchao on 2016/3/31.
 * E-mail:   cchao1024@163.com
 * Description:
 */
public class NewsFragmentsContainerPresenter implements IFragmentsContainerPresenter {
        FragmentsContainerView mView;
        IFragmentsContainerModel mModel;

        public NewsFragmentsContainerPresenter ( FragmentsContainerView view ) {
                mView = view;
                mModel = new NewsContainerFragmentModel ( this );
        }

        @Override
        public void setFragments ( ) {
                mView.onSetFragment ( mModel.getFragments ( ), mModel.getTitles ( ) );
        }
}
