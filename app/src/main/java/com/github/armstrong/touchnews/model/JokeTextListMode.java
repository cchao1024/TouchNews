package com.github.armstrong.touchnews.model;

import com.github.armstrong.touchnews.model.i.IListMode;
import com.github.armstrong.touchnews.util.NetRequestUtil;
import com.github.armstrong.touchnews.util.UriUtil;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by cchao on 2016/4/5.
 * E-mail:   cchao1024@163.com
 * Description:
 */
public class JokeTextListMode implements IListMode {
        NetRequestUtil.RequestListener mRequestListener;
        int mCurrentPage = 1;
        Map< String, String > param = new HashMap<> ( );
        Map< String, String > headers = new HashMap<> ( );

        public JokeTextListMode ( NetRequestUtil.RequestListener requestListener, String page ) {
                mRequestListener = requestListener;
                headers.put ( "apikey", "1bb91df70ccde8148a2c3da582ca9ff2" );
                param.put ( "page", page );
        }

        @Override
        public void loadRefreshData ( ) {
                mCurrentPage=1;
                loadMoreData ();
        }

        @Override
        public void loadMoreData ( ) {
                param.put ( "page", "" + mCurrentPage++ );
                NetRequestUtil.getInstance ( ).getJsonWithHeaders ( UriUtil.URL_JOKE_Text, param, headers, mRequestListener );
        }
}
