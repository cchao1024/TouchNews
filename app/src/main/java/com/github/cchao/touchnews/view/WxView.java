package com.github.cchao.touchnews.view;

import com.github.cchao.touchnews.javaBean.WxArticle.ShowapiResBodyBean.PagebeanBean.ContentlistBean;
import com.github.cchao.touchnews.util.Constant;

import java.util.List;

/**
 * Created by cchao on 2016/8/19.
 * E-mail:   cchao1024@163.com
 * Description:
 */
public interface WxView {
        /**
         * 获取数据
         *
         * @param articleList articleList
         */
        void onRefreshData ( List< ContentlistBean > articleList );

        /**
         * 添加数据
         *
         * @param articleList add articleList
         */
        void onAddMoreData ( List< ContentlistBean > articleList );

        /**
         * 显示信息  e.g. 没有网络、正在加载、异常
         *
         * @param INFOType 枚举值
         * @param infoText 提示的文本内容
         * @see Constant.INFO_TYPE
         */
        void showInfo ( Constant.INFO_TYPE INFOType, String infoText);
        void hideInfo();
}
