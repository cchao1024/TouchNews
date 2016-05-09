package com.github.armstrong.touchnews.ui.fragment;

import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
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

import com.github.armstrong.touchnews.R;
import com.github.armstrong.touchnews.presenter.ChatPresenter;
import com.github.armstrong.touchnews.presenter.i.IChatPresenter;
import com.github.armstrong.touchnews.ui.activity.HomeActivity;
import com.github.armstrong.touchnews.ui.fragment.base.BaseFragment;
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

        @Override
        public void onFirstUserVisible ( ) {
                super.onFirstUserVisible ( );
                mChatPresenter = new ChatPresenter ( this );
                mChatPresenter.onSendMessage ( "现在时间" );
        }

        @OnClick ( R.id.chat_send )
        public void click ( View v ) {
                switch ( v.getId ( ) ) {
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
