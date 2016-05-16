package com.github.cchao.touchnews.ui.fragment;

import android.os.Bundle;
import android.os.Handler;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.github.cchao.touchnews.R;
import com.github.cchao.touchnews.presenter.ChatPresenter;
import com.github.cchao.touchnews.presenter.i.IChatPresenter;
import com.github.cchao.touchnews.ui.fragment.base.BaseFragment;
import com.github.cchao.touchnews.view.IChatView;
import com.github.cchao.touchnews.widget.ChatItemView;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * Created by cchao on 2016/4/25.
 * E-mail:   cchao1024@163.com
 * Description: 聊天机器人
 */
public class ChatFragment extends BaseFragment implements IChatView {
        @Bind ( R.id.scroll_chat )
        ScrollView mScrollView;
        @Bind ( R.id.lyt_chat )
        LinearLayout mLinearContent;
        @Bind ( R.id.chat_edit )
        EditText mEditText;
        @Bind ( R.id.chat_send )
        TextView mSend;
        View mRootView;
        IChatPresenter mChatPresenter;

        @Override
        public void onCreate ( Bundle savedInstanceState ) {
                super.onCreate ( savedInstanceState );
                setHasOptionsMenu ( true );
        }

        @Override
        public View onCreateView ( LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState ) {
                super.onCreateView ( inflater, container, savedInstanceState );
                mRootView = inflater.inflate ( R.layout.fragment_chat, null );

                return mRootView;
        }

        private void initiation ( ) {
                mEditText.addTextChangedListener ( new TextWatcher ( ) {
                        @Override
                        public void beforeTextChanged ( CharSequence s, int start, int count, int after ) {

                        }
                        @Override
                        public void onTextChanged ( CharSequence s, int start, int before, int count ) {
                                //用户输入文本就改变发送键颜色
                                if ( count != 0 ) {
                                        mSend.setBackgroundResource ( R.drawable.bg_chat_send_normal );
                                        mSend.setTextColor ( getResources ( ).getColor ( R.color.textWhite ) );
                                } else {
                                        mSend.setBackgroundResource ( R.drawable.bg_chat_send_disabled );
                                        mSend.setTextColor ( getResources ( ).getColor ( R.color.textPrimaryGrey ) );
                                }
                        }

                        @Override
                        public void afterTextChanged ( Editable s ) {

                        }
                } );
        }

        /**
         *  滚动到Linear 底部
         */
        private void scrollToBottom ( ) {
                new Handler ( ).post ( new Runnable ( ) {
                        @Override
                        public void run ( ) {
                                mScrollView.fullScroll ( ScrollView.FOCUS_DOWN );
                        }
                } );
        }

        @Override
        public void onViewCreated ( View view, Bundle savedInstanceState ) {
                super.onViewCreated ( view, savedInstanceState );
                initiation ( );

        }

        @Override
        public void onCreateOptionsMenu ( Menu menu, MenuInflater inflater ) {
                inflater.inflate ( R.menu.menu_main, menu );
                super.onCreateOptionsMenu ( menu, inflater );
        }


        @Override
        public boolean onOptionsItemSelected ( MenuItem item ) {
                switch ( item.getItemId ( ) ) {
                        case R.id.action_search:
                                return true;
                }
                return false;
        }

        /**
         *  进入页面就发送时间请求
         */
        @Override
        public void onFirstUserVisible ( ) {
                super.onFirstUserVisible ( );
                mChatPresenter = new ChatPresenter ( this );
                mChatPresenter.onSendMessage ( "现在时间" );
                mToolbar.setTitle ( R.string.chat );
        }

        @OnClick ( R.id.chat_send )
        public void click ( View v ) {
                switch ( v.getId ( ) ) {
                        //发送
                        case R.id.chat_send:
                                mSend.setBackgroundResource ( R.color.textPrimaryGrey );
                                String message = mEditText.getText ( ).toString ( );
                                mEditText.setText ( "" );
                                if ( ! TextUtils.isEmpty ( message ) ) {
                                        /*TextView textView=new TextView (  mContext);
                                        textView.setText ( message );
                                        textView.setCompoundDrawablesWithIntrinsicBounds ( 0,0,R.drawable.icon_music,0);
                                        textView.setBackgroundResource ( R.drawable.icon_chat_request_0 );*/
                                        ChatItemView itemView = new ChatItemView ( mContext );
                                        itemView.setItem ( false, message );
                                        mLinearContent.addView ( itemView, new ViewGroup.LayoutParams ( ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup
                                                .LayoutParams.WRAP_CONTENT ) );
                                        mChatPresenter.onSendMessage ( message );

                                }
                                break;
                }
        }

        /**
         * 接收到响应
         * @param message 响应信息
         */
        @Override
        public void onReceiveRespond ( String message ) {
               /* TextView textView=new TextView (  mContext);
                textView.setText ( message );
                textView.setCompoundDrawablesWithIntrinsicBounds ( 0,0,R.drawable.icon_music,0);
                textView.setBackgroundResource ( R.drawable.bg_chat_respond);*/
                ChatItemView itemView = new ChatItemView ( mContext );
                itemView.setItem ( true, message );
                mLinearContent.addView ( itemView, new ViewGroup.LayoutParams ( ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT ) );
                scrollToBottom ( );
        }

}
