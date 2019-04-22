package com.github.cchao.touchnews.ui.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.github.cchao.touchnews.R;
import com.github.cchao.touchnews.javaBean.news.Contentlist;
import com.github.cchao.touchnews.ui.activity.HomeActivity;
import com.github.cchao.touchnews.ui.activity.NewsDetailActivity;
import com.github.cchao.touchnews.util.ImageUtil;

import java.util.List;

/**
 * Created by cchao on 2016/4/5.
 * E-mail:   cchao1024@163.com
 * Description:
 */
public class NewsListRecyclerAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private static final int TYPE_ITEM = 0;
    private static final int TYPE_FOOTER = 1;
    public Context mContext;
    public List<Contentlist> mData;
    public LayoutInflater mLayoutInflater;

//        private View.OnClickListener mOnClickListener;

    public NewsListRecyclerAdapter(Context context, List<Contentlist> data) {
        mContext = context;
        mData = data;
        mLayoutInflater = LayoutInflater.from(context);
    }

    /**
     * 获取当前滑动到的view 类型
     *
     * @param position 当前滑动位置
     * @return 内容或者footView
     */
    @Override
    public int getItemViewType(int position) {
        if (position + 1 == getItemCount()) {
            return TYPE_FOOTER;
        } else {
            return TYPE_ITEM;
        }
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == TYPE_ITEM) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_news, parent, false);
            return new MViewHolder(view);
        } else {
            //最后放置一个加载更多的 footView
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_view_footer, parent, false);
            return new RecyclerView.ViewHolder(view) {
            };
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof MViewHolder) {
            Contentlist contentEntity = mData.get(position);
            ((MViewHolder) holder).mTitle.setText(contentEntity.getTitle());
            ((MViewHolder) holder).mDescription.setText(contentEntity.getDesc());
            ImageUtil.displayImage(mContext, contentEntity.getImageurls().get(0).getUrl(), ((MViewHolder) holder).mImage);
        }
    }

    @Override
    public int getItemCount() {

        //多一个放加载更多
        return mData == null ? 1 : mData.size() + 1;
    }

    public class MViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public TextView mTitle;//标题
        public TextView mDescription;//描述内容
        public ImageView mImage;//预览图

        public MViewHolder(View view) {
            super(view);
            mTitle = (TextView) view.findViewById(R.id.tv_title);
            mDescription = (TextView) view.findViewById(R.id.tv_description);
            mImage = (ImageView) view.findViewById(R.id.imageView);
            view.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            //点击跳转新闻页
            Intent intent = new Intent(mContext, NewsDetailActivity.class);
            intent.putExtra("contentList", mData.get(this.getLayoutPosition()));
//                     View transitionView = view.findViewById(R.id.ivNews);
            ActivityOptionsCompat options =
                ActivityOptionsCompat.makeSceneTransitionAnimation((HomeActivity) mContext,
                    mImage, mContext.getString(R.string.transition__img));
            ActivityCompat.startActivity((HomeActivity) mContext, intent, options.toBundle());

        }
    }
}
