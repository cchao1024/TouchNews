package com.github.armstrong.touchnews.view;

import java.util.List;

/**
 * Created by cchao on 2016/4/1.
 * E-mail:   cchao1024@163.com
 * Description: View - 新闻频道Fragment
 */
public interface NewsListFragmentView {
        void refreshListData(List newsList);

        void addMoreListData(List newsList);
}
