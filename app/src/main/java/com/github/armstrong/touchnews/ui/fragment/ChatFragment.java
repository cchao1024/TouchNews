package com.github.armstrong.touchnews.ui.fragment;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.github.armstrong.touchnews.R;
import com.github.armstrong.touchnews.presenter.ChatPresenter;
import com.github.armstrong.touchnews.presenter.i.IChatPresenter;
import com.github.armstrong.touchnews.ui.fragment.base.BaseLazyFragment;
import com.github.armstrong.touchnews.view.IChatView;
import com.github.armstrong.touchnews.widget.ChatItemView;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by cchao on 2016/4/25.
 * E-mail:   cchao1024@163.com
 * Description: 聊天机器人
 */
public class ChatFragment extends BaseLazyFragment implements IChatView {
        @Bind ( R.id.lyt_chat)
        LinearLayout mLinearContent;
        @Bind ( R.id.chat_edit )
        EditText mEditText;
        @Bind ( R.id.chat_send )
        TextView mSend;
        View mRootView;
        IChatPresenter mChatPresenter;

        @Override
        public View onCreateView ( LayoutInflater inflater,  ViewGroup container,  Bundle savedInstanceState ) {
                super.onCreateView ( inflater, container, savedInstanceState );
                mRootView=inflater.inflate ( R.layout.fragment_chat,null );
                ButterKnife.bind ( this,mRootView );
                initiation();
                return mRootView;
        }

        private void initiation ( ) {
                mEditText.addTextChangedListener ( new TextWatcher ( ) {
                        @Override
                        public void beforeTextChanged ( CharSequence s, int start, int count, int after ) {

                        }
                        @Override
                        public void onTextChanged ( CharSequence s, int start, int before, int count ) {
                                if(count==0){
                                        mSend.setBackgroundResource ( R.color.textPrimaryGrey );
                                }else{
                                        mSend.setBackgroundResource ( R.color.colorPrimary );
                                }
                        }

                        @Override
                        public void afterTextChanged ( Editable s ) {

                        }
                } );
        }

        @Override
        public void onViewCreated ( View view,  Bundle savedInstanceState ) {
                super.onViewCreated ( view, savedInstanceState );
        }

        @Override
        public void onFirstUserVisible ( ) {
                super.onFirstUserVisible ( );
                mChatPresenter=new ChatPresenter (this);
                mChatPresenter.onSendMessage ("what time ?");
        }

        @OnClick (R.id.chat_send)
        public void click ( View v ) {
                switch ( v.getId ( ) ) {
                        case R.id.chat_send:
                                mSend.setBackgroundResource ( R.color.textPrimaryGrey );
                                String message=mEditText.getText ().toString ();
                                mEditText.setText ( "" );
                                if( !TextUtils.isEmpty ( message)){
                                        /*TextView textView=new TextView (  mContext);
                                        textView.setText ( message );
                                        textView.setCompoundDrawablesWithIntrinsicBounds ( 0,0,R.drawable.icon_music,0);
                                        textView.setBackgroundResource ( R.drawable.icon_chat_request_0 );*/
                                        ChatItemView itemView=new ChatItemView ( mContext);
                                        itemView.setItem ( false,message );
                                        mLinearContent.addView (itemView,new ViewGroup.LayoutParams ( ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup
                                                .LayoutParams.WRAP_CONTENT ));
                                        mChatPresenter.onSendMessage (message);
                                }
                                break;
                }
        }
        @Override
        public void onReceiveRespond ( String message ) {
               /* TextView textView=new TextView (  mContext);
                textView.setText ( message );
                textView.setCompoundDrawablesWithIntrinsicBounds ( 0,0,R.drawable.icon_music,0);
                textView.setBackgroundResource ( R.drawable.bg_chat_respond);*/
                ChatItemView itemView=new ChatItemView ( mContext);
                itemView.setItem ( true,message );
                mLinearContent.addView (itemView,new ViewGroup.LayoutParams ( ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT ));

        }
}
