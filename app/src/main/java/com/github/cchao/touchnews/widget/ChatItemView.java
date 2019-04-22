package com.github.cchao.touchnews.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.github.cchao.touchnews.R;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by cchao on 2016/4/26.
 * E-mail:   cchao1024@163.com
 * Description: 一条聊天信息的View
 */
public class ChatItemView extends LinearLayout {

    @Bind(R.id.iv_chat_item_left)
    ImageView mImageViewLeft;
    @Bind(R.id.iv_chat_item_right)
    ImageView mImageViewRight;
    @Bind(R.id.tv_chat_item)
    TextView mTextView;
    @Bind(R.id.layout_chat_item_tv)
    RelativeLayout mRelativeLayout;

    public ChatItemView(Context context) {
        this(context, null);
    }

    public ChatItemView(Context context, AttributeSet attrs) {
        super(context, attrs);
        LayoutInflater.from(context).inflate(R.layout.item_chat_view, this);
        ButterKnife.bind(this, this);
    }

    /**
     * @param isRespond 设置自定义聊天内容布局是否为返回的聊天语句样式
     * @param message   content
     */
    public void setItem(boolean isRespond, String message) {
        if (isRespond) {
            //响应的信息
            mImageViewRight.setVisibility(View.INVISIBLE);
            mTextView.setBackgroundResource(R.drawable.bg_chat_respond);

        } else {
            //请求信息
            mImageViewLeft.setVisibility(View.INVISIBLE);
            mTextView.setBackgroundResource(R.drawable.bg_chat_request);
            mRelativeLayout.setGravity(Gravity.RIGHT);

        }
        mTextView.setText(message);
    }
}
