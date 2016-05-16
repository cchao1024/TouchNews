package com.github.cchao.touchnews.presenter;

import com.android.volley.VolleyError;
import com.apkfuns.logutils.LogUtils;
import com.github.cchao.touchnews.model.ChatModel;
import com.github.cchao.touchnews.model.i.IChatModel;
import com.github.cchao.touchnews.presenter.i.IChatPresenter;
import com.github.cchao.touchnews.util.NetRequestUtil;
import com.github.cchao.touchnews.view.IChatView;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by cchao on 2016/4/25.
 * E-mail:   cchao1024@163.com
 * Description: 聊天机器人Presenter
 */
public class ChatPresenter implements IChatPresenter, NetRequestUtil.RequestListener {
        IChatModel mChatModel;
        IChatView mView;
        public ChatPresenter ( IChatView iChatView) {
                mView=iChatView;
                mChatModel=new ChatModel ( this,this );
        }

        @Override
        public void onSendMessage ( String message ) {
                mChatModel.onRequestMessage ( message );
        }

        @Override
        public void onReceive ( String receive ) {

        }

        @Override
        public void onResponse ( JSONObject response ) {
                LogUtils.i ( response );
                try {
                        if(response.getString ( "code" ).equals ( "100000" )){
                                mView.onReceiveRespond ( response.getString ( "text" ) );
                        }
                } catch ( JSONException e ) {
                        e.printStackTrace ( );
                }
        }

        @Override
        public void onError ( VolleyError error ) {

        }
}
