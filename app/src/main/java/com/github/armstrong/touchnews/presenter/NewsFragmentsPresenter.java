package com.github.armstrong.touchnews.presenter;

import com.github.armstrong.touchnews.model.i.IFragmentsContainerModel;
import com.github.armstrong.touchnews.model.NewsContainerFragmentModel;
import com.github.armstrong.touchnews.presenter.i.IFragmentsContainerPresenter;
import com.github.armstrong.touchnews.view.FragmentsContainerView;

/**
 * Created by cchao on 2016/3/31.
 * E-mail:   cchao1024@163.com
 * Description:
 */
public class NewsFragmentsPresenter implements IFragmentsContainerPresenter {
        FragmentsContainerView mView;
        IFragmentsContainerModel mModel;

        public NewsFragmentsPresenter ( FragmentsContainerView view ) {
                mView = view;
                mModel = new NewsContainerFragmentModel ( this );
        }

        @Override
        public void setFragments ( ) {
                mView.initFragment ( mModel.getFragments ( ), mModel.getTitles ( ) );
        }
}
