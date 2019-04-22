package com.github.cchao.touchnews.contract;

import com.github.cchao.touchnews.javaBean.joke.JokeTextRoot.Contentlist;
import com.github.cchao.touchnews.util.Constant;

import java.util.List;

/**
 * Created by cchao on 2016/8/26.
 * E-mail:   cchao1024@163.com
 * Description: 笑话集
 */
public interface JokeTextListContract {
    interface View {
        /**
         * 获取数据
         *
         * @param newsList newsList
         */
        void onRefreshData(List<Contentlist> newsList);

        /**
         * 添加数据
         *
         * @param newsList add newsList
         */
        void onReceiveMoreListData(List<Contentlist> newsList);

        /**
         * 显示信息  e.g. 没有网络、正在加载、异常
         *
         * @param INFOType 枚举值
         * @param infoText 提示的文本内容
         * @see Constant.INFO_TYPE
         */
        void showInfo(Constant.INFO_TYPE INFOType, String infoText);

        void hideInfo();

    }

    interface Presenter {
        void getRefreshData();

        void getMoreData();
    }
}
