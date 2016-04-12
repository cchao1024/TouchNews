package com.github.armstrong.touchnews.model;

import com.github.armstrong.touchnews.model.i.INewsListMode;
import com.github.armstrong.touchnews.util.NetRequestUtil;
import com.github.armstrong.touchnews.util.UriUtil;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by cchao on 2016/4/5.
 * E-mail:   cchao1024@163.com
 * Description:
 */
public class NewsListMode implements INewsListMode {
        NetRequestUtil.RequestListener mRequestListener;
        int mCurrentPage = 1;
        Map< String, String > param = new HashMap<> ( );
        Map< String, String > headers = new HashMap<> ( );

        public NewsListMode ( NetRequestUtil.RequestListener requestListener, String channelId ) {
                mRequestListener = requestListener;
                headers.put ( "apikey", "1bb91df70ccde8148a2c3da582ca9ff2" );
                param.put ( "channelId", channelId );
        }

        @Override
        public void loadRefreshData ( ) {
                mCurrentPage=1;
                loadMoreData ();
        }

        @Override
        public void loadMoreData ( ) {
                param.put ( "page", "" + mCurrentPage++ );
                NetRequestUtil.getInstance ( ).getJsonWithHeaders ( UriUtil.URL_NEWS, param, headers, mRequestListener );
        }
}
