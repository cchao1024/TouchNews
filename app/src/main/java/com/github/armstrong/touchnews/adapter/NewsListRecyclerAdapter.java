package com.github.armstrong.touchnews.adapter;

import android.app.Activity;
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

import com.github.armstrong.touchnews.R;
import com.github.armstrong.touchnews.javaBean.news.Contentlist;
import com.github.armstrong.touchnews.ui.activity.HomeActivity;
import com.github.armstrong.touchnews.ui.activity.NewsDetailActivity;
import com.github.armstrong.touchnews.util.ImageUtil;

import java.util.List;

/**
 * Created by cchao on 2016/4/5.
 * E-mail:   cchao1024@163.com
 * Description:
 */
public class NewsListRecyclerAdapter extends RecyclerView.Adapter< NewsListRecyclerAdapter.ViewHolder > {
        public Context mContext;
        public List< Contentlist > mData;
        public LayoutInflater mLayoutInflater;


//        private View.OnClickListener mOnClickListener;

        public NewsListRecyclerAdapter ( Context context, List< Contentlist > data ) {
                mContext = context;
                mData = data;
                mLayoutInflater = LayoutInflater.from ( context );
        }

        @Override
        public NewsListRecyclerAdapter.ViewHolder onCreateViewHolder ( ViewGroup parent, int viewType ) {
                View view = LayoutInflater.from ( parent.getContext ( ) ).inflate ( R.layout.news_item, parent, false );
                return new ViewHolder ( view );
        }

        @Override
        public void onBindViewHolder ( ViewHolder holder, int position ) {
                Contentlist contentEntity = mData.get ( position );
                holder.mTitle.setText ( contentEntity.getTitle ( ) );
                holder.mDescription.setText ( contentEntity.getDesc ( ) );
                ImageUtil.displayImage ( mContext, contentEntity.getImageurls ( ).get ( 0 ).getUrl ( ), holder.mImage );

        }

        /*public void setOnClickListener ( View.OnClickListener onClickListener ) {
                if ( onClickListener != null ) {
                        mOnClickListener = onClickListener;
                }
        }*/

        @Override
        public int getItemCount ( ) {
                return mData.size ( );
        }

        public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
                public TextView mTitle;
                public TextView mDescription;
                public ImageView mImage;

                public ViewHolder ( View view ) {
                        super ( view );
                        mTitle = ( TextView ) view.findViewById ( R.id.tv_title );
                        mDescription = ( TextView ) view.findViewById ( R.id.tv_description );
                        mImage = ( ImageView ) view.findViewById ( R.id.imageView );
                        view.setOnClickListener ( this );
                }

                @Override
                public void onClick ( View v ) {
                        /*if ( mOnClickListener != null ) {
                                mOnClickListener.onClick ( v );
                        }*/
                       Intent intent = new Intent ( mContext, NewsDetailActivity.class );
                        intent.putExtra ( "contentList", mData.get ( this.getLayoutPosition ( ) ) );
//                     View transitionView = view.findViewById(R.id.ivNews);
                        ActivityOptionsCompat options =
                                ActivityOptionsCompat.makeSceneTransitionAnimation ( ( HomeActivity )mContext,
                                        mImage, mContext.getString ( R.string.transition__img ) );

                        ActivityCompat.startActivity ( ( HomeActivity ) mContext, intent, options.toBundle ( ) );

                }
        }
}
