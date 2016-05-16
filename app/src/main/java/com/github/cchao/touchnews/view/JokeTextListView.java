package com.github.cchao.touchnews.view;

import com.github.cchao.touchnews.javaBean.joke.JokeTextRoot;
import com.github.cchao.touchnews.util.Constant;

import java.util.List;

/**
 * Created by cchao on 2016/4/1.
 * E-mail:   cchao1024@163.com
 * Description: View - joke 图片Fragment
 */
public interface JokeTextListView {
        /**
         * 获取数据
         *
         * @param newsList newsList
         */
        void refreshData ( List< JokeTextRoot.Contentlist > newsList );

        /**
         * 显示信息  e.g. 没有网络、正在加载、异常
         *
         * @param INFOType 枚举值
         * @param infoText 提示的文本内容
         * @see Constant.INFO_TYPE
         */
        void showInfo ( Constant.INFO_TYPE INFOType, String infoText );

        /**
         * 添加数据
         *
         * @param newsList add newsList
         */
        void addMoreListData ( List< JokeTextRoot.Contentlist > newsList );

        void hideInfo ( );
}
