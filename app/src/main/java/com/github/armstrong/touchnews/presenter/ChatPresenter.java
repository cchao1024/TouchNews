package com.github.armstrong.touchnews.presenter;

import com.android.volley.VolleyError;
import com.apkfuns.logutils.LogUtils;
import com.github.armstrong.touchnews.model.ChatModel;
import com.github.armstrong.touchnews.model.i.IChatModel;
import com.github.armstrong.touchnews.presenter.i.IChatPresenter;
import com.github.armstrong.touchnews.util.NetRequestUtil;
import com.github.armstrong.touchnews.view.IChatView;

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
