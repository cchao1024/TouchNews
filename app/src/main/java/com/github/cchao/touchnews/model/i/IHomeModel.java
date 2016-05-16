package com.github.cchao.touchnews.model.i;

import com.github.cchao.touchnews.util.NetRequestUtil;

import java.util.List;

/**
 * Created by cchao on 2016/3/31.
 * E-mail:   cchao1024@163.com
 * Description:
 */
public interface IHomeModel {
        List getFragments ( );

        void loadNavigation( NetRequestUtil.RequestListener requestListener);
}
