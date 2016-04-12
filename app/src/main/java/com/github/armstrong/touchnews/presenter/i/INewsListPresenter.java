package com.github.armstrong.touchnews.presenter.i;

/**
 * Created by cchao on 2016/4/1.
 * E-mail:   cchao1024@163.com
 * Description: 新闻列表
 */
public interface INewsListPresenter {
        void getRefreshData ( );
        void getFirstData ( );
        void getMoreData ( );
}
