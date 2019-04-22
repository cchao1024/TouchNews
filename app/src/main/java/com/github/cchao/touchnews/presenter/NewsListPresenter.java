package com.github.cchao.touchnews.presenter;

import com.github.cchao.touchnews.BaseApplication;
import com.github.cchao.touchnews.contract.NewListDataContract;
import com.github.cchao.touchnews.javaBean.news.Contentlist;
import com.github.cchao.touchnews.javaBean.news.NewsItemRoot;
import com.github.cchao.touchnews.javaBean.news.Pagebean;
import com.github.cchao.touchnews.util.Constant;
import com.github.cchao.touchnews.util.Keys;
import com.github.cchao.touchnews.util.LogUtil;
import com.github.cchao.touchnews.util.NetUtil;

import java.util.List;
import java.util.ListIterator;

import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

/**
 * Created by cchao on 2016/4/5.
 * E-mail:   cchao1024@163.com
 * Description:  新闻列表Presenter
 */
public class NewsListPresenter implements NewListDataContract.Presenter {
    private NewListDataContract.View mView;
    private int mCurrentPage = 1; //页码
    private String channelId; //新闻的分类ID

    public NewsListPresenter(NewListDataContract.View view, String channelId) {
        mView = view;
        this.channelId = channelId;
    }

    private void loadMoreData() {
        BaseApplication.getAppComponent().getBaiDuApiService().getNews(Keys.BAI_DU_API_KEY, channelId, "" + mCurrentPage++)
            .subscribeOn(Schedulers.newThread())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(new Action1<NewsItemRoot>() {
                @Override
                public void call(NewsItemRoot newsItemRoot) {
                    if (newsItemRoot.getShowapi_res_code() == 0 && newsItemRoot.getShowapi_res_body() != null) {
                        Pagebean pagebean = newsItemRoot.getShowapi_res_body().getPagebean();
                        List<Contentlist> contentlist = pagebean.getContentlist();
                        //没图你说个球呀
                        ListIterator<Contentlist> iterator = contentlist.listIterator();
                        while (iterator.hasNext()) {
                            //把没图的remove掉
                            if (iterator.next().getImageurls().size() <= 0) {
                                iterator.remove();
                            }
                        }
                        mView.hideInfo();
                        if (pagebean.getCurrentPage() == 1) {
                            mView.onRefreshData(contentlist);
                        } else {
                            mView.onReceiveMoreListData(contentlist);
                        }
                    }
                }
            }, new Action1<Throwable>() {
                @Override
                public void call(Throwable throwable) {
                    LogUtil.e(throwable.toString());
                }
            });
    }


    @Override
    public void getRefreshData() {
        //无网刷新数据>底部显示SnackBar>点击打开设置界面
        if (!NetUtil.isConnected()) {
            mView.showInfo(Constant.INFO_TYPE.NO_NET, null);
        } else {
            mView.showInfo(Constant.INFO_TYPE.LOADING, null);
            mCurrentPage = 1;
            loadMoreData();
        }
    }

    @Override
    public void getMoreData() {
        loadMoreData();
    }
}
